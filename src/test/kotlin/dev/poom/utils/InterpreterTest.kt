package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InterpreterTest {
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

    @Test
    fun and() {
        val m = Interpreter()
        m.mov(Register.ECX, 0b00001100)
        m.and(Register.ECX, 0b00011001)

        assertEquals(0b00001000, m.value(Register.ECX))
    }

    @Test
    fun or() {
        val m = Interpreter()
        m.mov(Register.ECX, 0b00001100)
        m.or(Register.ECX,  0b00011001)

        assertEquals(0b00011101, m.value(Register.ECX))
    }

    @Test
    fun xor() {
        val m = Interpreter()
        m.mov(Register.ECX, 0b00001100)
        m.xor(Register.ECX, 0b00011001)

        assertEquals(0b00010101, m.value(Register.ECX))
    }

    @Test
    fun not() {
        val m = Interpreter()
        m.mov(Register.ECX, 0b00001100)
        m.not(Register.ECX)

        assertEquals(-13, m.value(Register.ECX))
    }

    @Test
    fun stack() {
        val m = Interpreter()
        m.push(50)
        assertEquals(4, m.value(Register.ESP))

        m.push(100)
        assertEquals(8, m.value(Register.ESP))

        m.pop(Register.EAX)
        assertEquals(4, m.value(Register.ESP))
        assertEquals(100, m.value(Register.EAX))

        m.pop(Register.EAX)
        assertEquals(0, m.value(Register.ESP))
        assertEquals(50, m.value(Register.EAX))
    }

    @Test
    fun `compare equal`() {
        // 10 == 50
        val m = Interpreter()
        m.cmp(10, 10)

        // ZF = 1, CF = 0
        val v = FlagView(m.value(Register.FLAGS))
        assertEquals(true to false, v.get(Flag.ZERO) to v.get(Flag.CARRY))
    }

    @Test
    fun `compare less than`() {
        // 10 < 50
        val m = Interpreter()
        m.cmp(10, 50)

        // ZF = 0, CF = 1
        val v = FlagView(m.value(Register.FLAGS))
        assertEquals(false to true, v.get(Flag.ZERO) to v.get(Flag.CARRY))
    }

    @Test
    fun `compare more than`() {
        // 100 > 20
        val m = Interpreter()
        m.cmp(100, 20)

        // ZF = 0, CF = 0
        val v = FlagView(m.value(Register.FLAGS))
        assertEquals(false to false, v.get(Flag.ZERO) to v.get(Flag.CARRY))
    }
}