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
            cmp eax, 5
            jb 1
            mov ebx, 69
        """)

        // Expect loop to increment EAX five times
        assertEquals(5, o.m.value(Register.EAX))

        // Expect last line of instruction to be run
        assertEquals(69, o.m.value(Register.EBX))

        // Expect the zero flag from the last comparison to be set (
        assertEquals(true, o.m.flags[Flag.ZERO])
    }
}