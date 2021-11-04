package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InterpreterTest {
    @Test
    fun valueOf() {
        val m = Interpreter()
        m.state.registers[Register.EAX] = 50

        assertEquals(50, m.valueOf(Register.EAX))
    }

    @Test
    fun mov() {
        val m = Interpreter()
        m.mov(Register.EAX, 20)

        assertEquals(20, m.valueOf(Register.EAX))

        m.mov(Register.ECX, 50)
        m.mov(Register.EAX, Register.ECX)

        assertEquals(50, m.valueOf(Register.EAX))
    }

    @Test
    fun add() {
        val m = Interpreter()
        m.mov(Register.EAX, 80)
        m.add(Register.EAX, 50)

        assertEquals(130, m.valueOf(Register.EAX))
    }

    @Test
    fun sub() {
        val m = Interpreter()
        m.mov(Register.ECX, 80)
        m.sub(Register.ECX, 50)

        assertEquals(30, m.valueOf(Register.ECX))
    }

    @Test
    fun inc() {
        val m = Interpreter()
        m.mov(Register.ECX, 10)
        m.inc(Register.ECX)

        assertEquals(11, m.valueOf(Register.ECX))
    }

    @Test
    fun dec() {
        val m = Interpreter()
        m.mov(Register.ECX, 10)
        m.dec(Register.ECX)

        assertEquals(9, m.valueOf(Register.ECX))
    }
}