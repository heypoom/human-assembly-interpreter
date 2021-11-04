package dev.poom.utils

import dev.poom.utils.Instruction.*

class QuestionMapper(val m: MachineState = MachineState()) {
    fun from(code: String): String = from(InstructionParser.parse(code))
    fun from(ops: List<Op>): String = ops.map(::from).joinToString("\n")

    fun from(op: Op): String? {
        val (instruction, args) = op

        val dst = m.value(args[0])
        val src = m.value(args.getOrNull(1) ?: 0)

        return when (instruction) {
            MOV -> null
            ADD -> "What is $dst + $src?"
            SUB -> "What is $dst - $src?"
            MUL -> "What is $dst * $src?"
            DIV -> "What is $dst / $src?"
            else -> null
        }
    }
}