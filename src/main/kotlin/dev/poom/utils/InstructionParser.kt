package dev.poom.utils

data class Op(val instruction: Instruction, val values: List<*>)

inline fun <reified T : Enum<T>> intoEnum(name: String): T? {
    return try {
        enumValueOf<T>(name.uppercase())
    } catch (e: IllegalArgumentException) { null }
}

object InstructionParser {
    fun parse(code: String): List<Op> {
        val list: MutableList<Op> = mutableListOf()

        for (line in code.trimIndent().lines()) {
            val s = line.replace(",", "").split(" ")
            val (operationText, valuesText) = Pair(s.first(), s.drop(1))

            val instruction = instructionOf(operationText) ?: continue
            val values = valuesOf(valuesText)

            list.add(Op(instruction, values))
        }

        return list
    }

    private fun valuesOf(strings: List<String>) = strings.map { registerOf(it) ?: it.toInt() }
    private fun instructionOf(name: String) = intoEnum<Instruction>(name)
    private fun registerOf(name: String) = intoEnum<Register>(name)
}