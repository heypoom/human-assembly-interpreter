package dev.poom.utils

enum class Register {
    /** Stores the flags. */
    FLAGS,

    /** Accumulator */
    EAX,

    /** Counter */
    ECX,

    /** Data */
    EDX,

    /** Base */
    EBX,

    /** Instruction Pointer (Program Counter) */
    EIP,

    /** Stack Pointer */
    ESP,

    /** Stack Base Pointer */
    EBP,

    /** Source */
    ESI,

    /** Destination */
    EDI
}