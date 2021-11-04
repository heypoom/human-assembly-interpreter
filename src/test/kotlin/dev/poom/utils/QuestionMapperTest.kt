package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuestionMapperTest {
    @Test
    fun from() {
        val state = MachineState(mutableMapOf(Register.EAX to 40))
        val qm = QuestionMapper(state)
        val question = qm.from("add eax, 5")

        println(question)
    }
}