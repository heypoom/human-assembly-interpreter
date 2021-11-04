package dev.poom.utils

enum class Flag(val i: Int) {
    /** Carry Flag (CF) */
    CARRY(0),

    /** Parity Flag (PF) */
    PARITY(2),

    /** Adjust Flag (AF) */
    ADJUST(4),

    /** Zero Flag (ZF) */
    ZERO(6),

    /** Sign Flag (SF) */
    SIGN(7),

    TRAP(8)
}
