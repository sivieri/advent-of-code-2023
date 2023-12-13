package me.sivieri.aoc2023.day13

class PatternInterpreter(data: String) {

    private val patterns = data.split("\n\n").map { Pattern(it) }

    fun findReflectionPatterns(): Int = patterns.sumOf {
        it.columnsLeftOfReflection() + 100 * it.rowsAboveReflection()
    }

}