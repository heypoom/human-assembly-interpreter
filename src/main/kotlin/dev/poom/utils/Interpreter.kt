package dev.poom.utils

import dev.poom.utils.Flag.*
import dev.poom.utils.Register.*

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

    fun jmp(line: Int) = mov(EIP, line)

    fun cmp(reg: Register, value: Int) = cmp(value(reg), value)

    val flags get() = FlagView(value(FLAGS))

    fun cmp(dst: Int, src: Int) {
        val (zero, carry) = when {
            dst == src -> true to false
            dst < src -> false to true
            else -> false to false
        }

        val status = flags
            .set(ZERO, zero)
            .set(CARRY, carry)
            .bits

        mov(FLAGS, status)
    }

    private fun jumpIf(dst: Int, condition: Boolean) {
        if (condition) jmp(dst)
    }

    fun ja(dst: Int) = jumpIf(dst, !flags[CARRY] && !flags[ZERO])
    fun jae(dst: Int) = jumpIf(dst, !flags[CARRY])

    fun jb(dst: Int) = jumpIf(dst, flags[CARRY])
    fun jbe(dst: Int) = jumpIf(dst, flags[CARRY] || flags[ZERO])

    fun je(dst: Int) = jumpIf(dst, flags[ZERO])
    fun jne(dst: Int) = jumpIf(dst, !flags[ZERO])

    fun jl(dst: Int) = jumpIf(dst, flags[SIGN] != flags[OVERFLOW])
    fun jle(dst: Int) = jumpIf(dst, flags[ZERO] || (flags[SIGN] != flags[OVERFLOW]))

    fun jg(dst: Int) = jumpIf(dst, flags[ZERO] && (flags[SIGN] == flags[OVERFLOW]))
    fun jge(dst: Int) = jumpIf(dst, flags[SIGN] == flags[OVERFLOW])

    fun push(value: Int) {
        add(ESP, 4)

        val sp = value(ESP)
        state.stack[sp] = value
    }

    fun pop(dst: Register) {
        val sp = value(ESP)
        val value = state.stack[sp] ?: 0
        mov(dst, value)

        sub(ESP, 4)
    }

    fun call(dst: Int) {
        push(value(EIP))
        mov(EIP, dst)
    }

    fun int(value: Int): Unit = TODO()
}
