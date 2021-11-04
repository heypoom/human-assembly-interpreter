package dev.poom.utils

import java.util.*
import dev.poom.utils.Instruction.*

data class HumanTask(val line: Int, val question: String)

class ComputeManager(val code: String) {
    // List of assembly instructions.
    // Each line of instruction should correspond to the instruction pointer (EIP).
    val instructions: List<Op> = InstructionParser.parse(code)

    // Interpreter stores the machine state and provides method to alter its state.
    val interpreter: Interpreter = Interpreter()

    // Tasks for humans to do.
    val taskQueue: Queue<HumanTask> = LinkedList()

    // Value of the Instruction Pointer.
    // Indicates which line of code is being executed.
    // Note that since assembly has jumps, this will not be linear.
    val ip get() = interpreter[Register.EIP]

    val currentOp get() = instructions[ip]

    val isCorrect get(): Boolean {
        val oi = OperationInterpreter()
        oi.run(instructions)

        return interpreter.state == oi.m.state
    }

    fun start() {
        var count = 0

        while (ip < instructions.size) {
            count++
            if (count > 20) break

            println("OP = $currentOp")
            println("IP = $ip out of ${instructions.size}")

            createTask(currentOp)

            if (needsInterpreter(currentOp.instruction)) continue

            val task = taskQueue.poll() ?: continue

            println("Question is: ${task.question}")

            val op = instructions[task.line]
            val answer = readLine()?.trim() ?: return
            processAnswer(op, answer)
        }
    }

    fun createTask(op: Op) {
        // If the question is not present,
        // it cannot be computed by humans and thus require the interpreter.
        if (needsInterpreter(op.instruction)) return run(op)

        val mapper = QuestionMapper(interpreter.state)
        val question = mapper.from(op) ?: return

        taskQueue.add(HumanTask(ip, question))

        println("Q = $taskQueue")
    }

    fun run(op: Op) = OperationInterpreter(interpreter).run(op)

    fun needsInterpreter(instruction: Instruction) =
        instruction in listOf(MOV, INT, JMP)

    fun processAnswer(op: Op, answer: String) {
        val numAnswer = answer.toInt()

        val (src) = op.args
        if (src !is Register) return

        interpreter.mov(src, numAnswer)
        interpreter.inc(Register.EIP)
    }
}