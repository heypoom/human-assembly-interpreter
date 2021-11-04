package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OperationInterpreterTest {
    @Test
    fun `execute sequence of operations`() {
        val oi = OperationInterpreter()
        oi.run(Op(Instruction.MOV, listOf(Register.EAX, 50)))
        oi.run(Op(Instruction.INC, listOf(Register.EAX)))

        assertEquals(51, oi.m.valueOf(Register.EAX))
    }

    @Test
    fun `execute code from string`() {
        val oi = OperationInterpreter()

        oi.run("""
            mov eax, 69
            mov ecx, eax
            inc ecx
            inc ecx
        """)

        assertEquals(71, oi.m.valueOf(Register.ECX))
    }
}