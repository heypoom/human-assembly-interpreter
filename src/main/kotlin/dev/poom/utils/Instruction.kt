package dev.poom.utils

enum class Instruction {
    /** Move */
    MOV,

    /** Increment the value by 1 */
    INC,

    /** Decrement the value by 1 */
    DEC,

    /** Add two values */
    ADD,

    /** Subtract two values */
    SUB,

    /** Multiply two values */
    MUL,

    /** Divide two values */
    DIV,

    /** Interrupt */
    INT,

    /** Call a procedure by `PUSH eip` */
    CALL,

    /** Logical AND */
    AND,

    /** Logical OR */
    OR,

    /** Logical NOT */
    NOT,

    /** Logical XOR */
    XOR,

    /** Shift Left */
    SHL,

    /** Shift Right */
    SHR,

    CMP,

    /** Push data on to the stack */
    PUSH,

    /** Pop data from the stack */
    POP,

    /** Load effective address */
    LEA,

    JMP,

    /** Jump if value is greater than n (x > n) */
    JG,

    /** Jump if value is greater than or equal n (x >= n) */
    JGE,

    /** Jump if value is not greater than n */
    JNG,

    /** Jump if value is not greater than or equal n */
    JNGE,

    /** Jump if value is less than n (x < n) */
    JL,

    /** Jump if value is less than or equal n (x <= n) */
    JLE,

    /** Jump if value is not less than n */
    JNL,

    /** Jump if value is not less than or equal n */
    JNLE,

    /** Jump if value is equal */
    JE,

    /** Jump if value is not equal */
    JNE,

    /** Jump if value is zero */
    JZ,

    /** Jump if value is not zero */
    JNZ,
}
