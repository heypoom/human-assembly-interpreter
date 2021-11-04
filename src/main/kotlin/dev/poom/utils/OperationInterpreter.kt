package dev.poom.utils

import dev.poom.utils.Instruction.*

class OperationInterpreter(val m: Interpreter = Interpreter()) {
    private val ip get() = m.value(Register.EIP)

    fun run(code: String) = run(InstructionParser.parse(code))

    fun run(ops: List<Op>) {
        while (ip < ops.size) run(ops[ip])
    }

    fun run(op: Op) {
        val (instruction, args) = op

        val (dst) = args

        if (dst !is Register) {
            val value = value(dst)

            when (instruction) {
                PUSH -> m.push(value)
                JMP -> m.jmp(value)

                JG -> m.jg(value)
                JGE -> m.jge(value)

                JNG -> m.jle(value)
                JNGE -> m.jl(value)

                JL -> m.jl(value)
                JLE -> m.jle(value)

                JNL -> m.jge(value)
                JNLE -> m.jg(value)

                JE -> m.je(value)
                JNE -> m.jne(value)

                JZ -> m.je(value)
                JNZ -> m.jne(value)

                JA -> m.ja(value)
                JAE -> m.jae(value)

                JB -> m.jb(value)
                JBE -> m.jbe(value)

                JNA -> m.jbe(value)
                JNB -> m.jae(value)

                JC -> m.jb(value)
                JNC -> m.jae(value)

                else -> TODO()
            }

            m.inc(Register.EIP)

            return
        }

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

            POP -> m.pop(dst)

            INT -> TODO()
            CALL -> TODO()
            LEA -> TODO()

            CMP -> m.cmp(dst, src)

            else -> TODO()
        }

        m.inc(Register.EIP)
    }

    private fun value(n: Any?): Int {
        if (n is Register) return m.value(n)
        if (n is Int) return n

        return 0
    }
}