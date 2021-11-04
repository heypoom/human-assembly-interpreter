package dev.poom.utils

data class MachineState(
    val registers: MutableMap<Register, Int> = mutableMapOf(),
    val stack: MutableMap<Int, Int> = mutableMapOf()
)

class Interpreter(val state: MachineState = MachineState()) {
    fun mov(destination: Register, value: Int) {
        state.registers[destination] = value
    }

    fun mov(destination: Register, source: Register) = mov(destination, valueOf(source))
    fun add(reg: Register, value: Int) = mov(reg, valueOf(reg) + value)
    fun sub(reg: Register, value: Int) = add(reg, -value)

    fun inc(reg: Register) = add(reg, 1)
    fun dec(reg: Register) = sub(reg, 1)

    fun nop() {}

    fun valueOf(register: Register): Int = state.registers[register] ?: 0
}
