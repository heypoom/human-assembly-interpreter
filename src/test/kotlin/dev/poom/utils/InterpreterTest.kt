package dev.poom.utils

import dev.poom.utils.Register.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class InterpreterTest {
    @Test
    fun mov() {
        val m = Interpreter()
        m.mov(EAX, 20)

        assertEquals(20, m[EAX])

        m.mov(ECX, 50)
        m.mov(EAX, ECX)

        assertEquals(50, m[EAX])
    }

    @Test
    fun add() {
        val m = Interpreter()
        m.mov(EAX, 80)
        m.add(EAX, 50)

        assertEquals(130, m[EAX])
    }

    @Test
    fun sub() {
        val m = Interpreter()
        m.mov(ECX, 80)
        m.sub(ECX, 50)

        assertEquals(30, m[ECX])
    }

    @Test
    fun inc() {
        val m = Interpreter()
        m.mov(ECX, 10)
        m.inc(ECX)

        assertEquals(11, m[ECX])
    }

    @Test
    fun dec() {
        val m = Interpreter()
        m.mov(ECX, 10)
        m.dec(ECX)

        assertEquals(9, m[ECX])
    }

    @Test
    fun shl() {
        val m = Interpreter()
        m.mov(EAX, 0b11010100)

        m.shl(EAX)
        assertEquals(0b110101000, m[EAX])

        m.shl(EAX, 3)
        assertEquals(0b110101000000, m[EAX])
    }

    @Test
    fun shr() {
        val m = Interpreter()
        m.mov(EAX, 0b11010100)

        m.shr(EAX)
        assertEquals(0b01101010, m[EAX])

        m.shr(EAX, 3)
        assertEquals(0b1101, m[EAX])
    }

    @Test
    fun and() {
        val m = Interpreter()
        m.mov(ECX, 0b00001100)
        m.and(ECX, 0b00011001)

        assertEquals(0b00001000, m[ECX])
    }

    @Test
    fun or() {
        val m = Interpreter()
        m.mov(ECX, 0b00001100)
        m.or(ECX,  0b00011001)

        assertEquals(0b00011101, m[ECX])
    }

    @Test
    fun xor() {
        val m = Interpreter()
        m.mov(ECX, 0b00001100)
        m.xor(ECX, 0b00011001)

        assertEquals(0b00010101, m[ECX])
    }

    @Test
    fun not() {
        val m = Interpreter()
        m.mov(ECX, 0b00001100)
        m.not(ECX)

        assertEquals(-13, m[ECX])
    }

    @Test
    fun stack() {
        val m = Interpreter()
        m.push(50)
        assertEquals(4, m[ESP])

        m.push(100)
        assertEquals(8, m[ESP])

        m.pop(EAX)
        assertEquals(4, m[ESP])
        assertEquals(100, m[EAX])

        m.pop(EAX)
        assertEquals(0, m[ESP])
        assertEquals(50, m[EAX])
    }

    @Test
    fun `compare equal`() {
        // 10 == 50
        val m = Interpreter()
        m.cmp(10, 10)

        // ZF = 1, CF = 0
        assertEquals(true to false, m.flags[Flag.ZERO] to m.flags[Flag.CARRY])
    }

    @Test
    fun `compare less than`() {
        // 10 < 50
        val m = Interpreter()
        m.cmp(10, 50)

        // ZF = 0, CF = 1
        assertEquals(false to true, m.flags[Flag.ZERO] to m.flags[Flag.CARRY])
    }

    @Test
    fun `compare more than`() {
        // 100 > 20
        val m = Interpreter()
        m.cmp(100, 20)

        // ZF = 0, CF = 0
        assertEquals(false to false, m.flags[Flag.ZERO] to m.flags[Flag.CARRY])
    }
}