package dev.poom.utils

data class Op(val instruction: Instruction, val args: List<*>)

inline fun <reified T> nullify(fn: () -> T): T? =
    try { fn() } catch (e: Exception) { null }

inline fun <reified T : Enum<T>> intoEnum(name: String): T? =
    nullify { enumValueOf<T>(name.uppercase()) }

fun intoNumeric(value: String): Int? =
    nullify { value.toInt() }

object InstructionParser {
    fun parse(code: String): List<Op> {
        val list: MutableList<Op> = mutableListOf()

        for (line in code.trimIndent().lines()) {
            val op = parseLine(line) ?: continue
            list.add(op)
        }

        return list
    }

    fun parseLine(code: String): Op? {
        val s = code.replace(",", "").split(" ")
        val (instructionText, argsText) = s.first() to s.drop(1)

        val instruction = instructionOf(instructionText) ?: return null
        val args = argsOf(argsText)

        return Op(instruction, args)
    }


    private fun registerOf(name: String) = intoEnum<Register>(name)
    private fun instructionOf(name: String) = intoEnum<Instruction>(name)
    private fun argsOf(strings: List<String>) = strings.map { registerOf(it) ?: intoNumeric(it) }
}