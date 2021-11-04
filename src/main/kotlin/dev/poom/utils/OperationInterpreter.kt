package dev.poom.utils

import dev.poom.utils.Instruction.*

class OperationInterpreter(val m: Interpreter = Interpreter()) {
    fun run(code: String) = run(InstructionParser.parse(code))

    fun run(ops: List<Op>) = ops.forEach { run(it) }

    fun run(op: Op) {
        val (instruction, args) = op

        val (dst) = args
        if (dst !is Register) return

        val src = value(args.getOrNull(1))

        when (instruction) {
            MOV -> m.mov(dst, src)

            ADD -> m.add(dst, src)
            SUB -> m.sub(dst, src)
            MUL -> m.mul(dst, src)
            DIV -> m.div(dst, src)

            INC -> m.inc(dst)
            DEC -> m.dec(dst)

            AND -> m.and(dst, src)
            OR -> m.or(dst, src)
            XOR -> m.xor(dst, src)
            NOT -> m.not(dst)

            SHL -> m.shl(dst, src)
            SHR -> m.shr(dst, src)

            INT -> TODO()
            CALL -> TODO()

            CMP -> TODO()
            PUSH -> TODO()
            POP -> TODO()
            LEA -> TODO()

            JMP -> TODO()

            JG -> TODO()
            JGE -> TODO()

            JNG -> TODO()
            JNGE -> TODO()

            JL -> TODO()
            JLE -> TODO()

            JNL -> TODO()
            JNLE -> TODO()

            JE -> TODO()
            JNE -> TODO()

            JZ -> TODO()
            JNZ -> TODO()
        }
    }

    private fun value(n: Any?): Int {
        if (n is Register) return m.value(n)
        if (n is Int) return n

        return 0
    }
}