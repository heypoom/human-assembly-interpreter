package dev.poom.utils

data class Op(val instruction: Instruction, val values: List<*>)

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
            val s = line.replace(",", "").split(" ")
            val (operationText, valuesText) = s.first() to s.drop(1)

            val instruction = instructionOf(operationText) ?: continue
            val values = valuesOf(valuesText)

            list.add(Op(instruction, values))
        }

        return list
    }


    private fun registerOf(name: String) = intoEnum<Register>(name)
    private fun instructionOf(name: String) = intoEnum<Instruction>(name)
    private fun valuesOf(strings: List<String>) = strings.map { registerOf(it) ?: intoNumeric(it) }
}