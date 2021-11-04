package dev.poom.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ConditionalJumpTest {
    @Test
    fun `conditional jump`() {
        val o = OperationInterpreter()
        o.run("""
            mov eax, 1
            inc eax
            cmp eax, 10
            jb 1
            add eax, 59
        """)

        println(o.m.state)
        assertEquals(69, o.m.value(Register.EAX))
    }
}