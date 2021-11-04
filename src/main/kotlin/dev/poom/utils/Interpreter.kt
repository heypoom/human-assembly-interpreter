package dev.poom.utils

data class MachineState(
    val registers: MutableMap<Register, Int> = mutableMapOf(),
    val stack: MutableMap<Int, Int> = mutableMapOf()
)

class Interpreter(val state: MachineState = MachineState()) {
    fun mov(destination: Register, value: Int) {
        state.registers[destination] = value
    }

    fun mov(destination: Register, source: Register) = mov(destination, value(source))

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

    fun shl(reg: Register, value: Int) = mov(reg, value(reg) shl value)
    fun shr(reg: Register, value: Int) = mov(reg, value(reg) shr value)

    fun nop() {}

    fun value(register: Register): Int = state.registers[register] ?: 0
}
