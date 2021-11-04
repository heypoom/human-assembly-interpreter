package dev.poom.utils

enum class Flag(val i: Int) {
    CARRY(0),
    PARITY(2),
    ADJUST(4),
    ZERO(6),
    SIGN(7),
    TRAP(8)
}
