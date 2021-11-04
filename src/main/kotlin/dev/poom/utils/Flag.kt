package dev.poom.utils

enum class Flag(val i: Int) {
    /**
     * Carry Flag (CF)
     *
     * Set when the result of unsigned arithmetic is too large,
     * or when subtraction requires a borrow.
     **/
    CARRY(0),

    /** Parity Flag (PF) */
    PARITY(2),

    /** Adjust Flag (AF) */
    ADJUST(4),

    /** Zero Flag (ZF): Set when the result of an operation is zero */
    ZERO(6),

    /** Sign Flag (SF): Set when the high bit of destination is set, indicating a negative result */
    SIGN(7),

    /** Overflow Flag (OF): Set when signed arithmetic generates an out-of-range result */
    OVERFLOW(11)
}
