package dev.poom.utils

import java.util.*
import kotlin.streams.toList

data class MachineState(
    val registers: MutableMap<Register, Int> = mutableMapOf(),
    val stack: MutableMap<Int, Int> = mutableMapOf()
) {
    fun value(n: Any?): Int {
        if (n is Register) return registers[n] ?: 0
        if (n is Int) return n

        return 0
    }
}

class Interpreter(val state: MachineState = MachineState()) {
    fun value(register: Register): Int = state.registers[register] ?: 0

    fun mov(dst: Register, value: Int) = state.registers.set(dst, value)
    fun mov(dst: Register, source: Register) = mov(dst, value(source))

    fun add(reg: Register, value: Int) = mov(reg, value(reg) + value)
    fun sub(reg: Register, value: Int) = add(reg, -value)
    fun mul(reg: Register, value: Int) = mov(reg, value(reg) * value)
    fun div(reg: Register, value: Int) = mov(reg, value(reg) / value)

    fun inc(reg: Register) = add(reg, 1)
    fun dec(reg: Register) = sub(reg, 1)

    fun and(reg: Register, value: Int) = mov(reg, value(reg) and value)
    fun or(reg: Register, value: Int) = mov(reg, value(reg) or value)
    fun xor(reg: Register, value: Int) = mov(reg, value(reg) xor value)
    fun not(reg: Register) = mov(reg, value(reg).inv())

    fun shl(reg: Register, value: Int = 1) = mov(reg, value(reg) shl value)
    fun shr(reg: Register, value: Int = 1) = mov(reg, value(reg) shr value)

    fun jmp(line: Int) = mov(Register.EIP, line)

    fun cmp(reg: Register, value: Int) = cmp(value(reg), value)

    fun cmp(dst: Int, src: Int) {
        val (zero, carry) = when {
            dst == src -> true to false
            dst < src -> false to true
            else -> false to false
        }

        val status = FlagView(value(Register.FLAGS))
            .set(Flag.ZERO, zero)
            .set(Flag.CARRY, carry)
            .bits

        mov(Register.FLAGS, status)
    }

    // TODO: Set zero bits
    fun test(value: Int) {
        mov(Register.FLAGS, 0b01010101)
    }

    fun jne(to: Int) {

    }

    fun push(value: Int) {
        add(Register.ESP, 4)

        val sp = value(Register.ESP)
        state.stack[sp] = value
    }

    fun pop(dst: Register) {
        val sp = value(Register.ESP)
        val value = state.stack[sp] ?: 0
        mov(dst, value)

        sub(Register.ESP, 4)
    }
}
