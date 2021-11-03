package dev.poom.utils

import org.junit.jupiter.api.Test

internal class InstructionParserTest {
    @Test
    fun parse() {
        val actual = InstructionParser.parse("""
            mov eax, 10
            mov ecx, eax
            mul ecx, ecx
        """)

        val expected = listOf(
            Op(Instruction.MOV, listOf(Register.EAX, 10)),
            Op(Instruction.MOV, listOf(Register.ECX, Register.EAX)),
            Op(Instruction.MUL, listOf(Register.ECX, Register.ECX))
        )

        assert(expected == actual)
    }
}