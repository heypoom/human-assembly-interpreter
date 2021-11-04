package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InterpreterTest {
    @Test
    fun value() {
        val m = Interpreter()
        m.state.registers[Register.EAX] = 50

        assertEquals(50, m.value(Register.EAX))
    }

    @Test
    fun mov() {
        val m = Interpreter()
        m.mov(Register.EAX, 20)

        assertEquals(20, m.value(Register.EAX))

        m.mov(Register.ECX, 50)
        m.mov(Register.EAX, Register.ECX)

        assertEquals(50, m.value(Register.EAX))
    }

    @Test
    fun add() {
        val m = Interpreter()
        m.mov(Register.EAX, 80)
        m.add(Register.EAX, 50)

        assertEquals(130, m.value(Register.EAX))
    }

    @Test
    fun sub() {
        val m = Interpreter()
        m.mov(Register.ECX, 80)
        m.sub(Register.ECX, 50)

        assertEquals(30, m.value(Register.ECX))
    }

    @Test
    fun inc() {
        val m = Interpreter()
        m.mov(Register.ECX, 10)
        m.inc(Register.ECX)

        assertEquals(11, m.value(Register.ECX))
    }

    @Test
    fun dec() {
        val m = Interpreter()
        m.mov(Register.ECX, 10)
        m.dec(Register.ECX)

        assertEquals(9, m.value(Register.ECX))
    }

    @Test
    fun shl() {
        val m = Interpreter()
        m.mov(Register.EAX, 0b11010100)

        m.shl(Register.EAX)
        assertEquals(0b110101000, m.value(Register.EAX))

        m.shl(Register.EAX, 3)
        assertEquals(0b110101000000, m.value(Register.EAX))
    }

    @Test
    fun shr() {
        val m = Interpreter()
        m.mov(Register.EAX, 0b11010100)

        m.shr(Register.EAX)
        assertEquals(0b01101010, m.value(Register.EAX))

        m.shr(Register.EAX, 3)
        assertEquals(0b1101, m.value(Register.EAX))
    }
}