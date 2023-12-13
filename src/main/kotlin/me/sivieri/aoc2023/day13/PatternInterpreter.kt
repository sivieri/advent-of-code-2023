package me.sivieri.aoc2023.day13

class PatternInterpreter(data: String) {

    private val patterns = data.split("\n\n").map { Pattern(it) }

    fun findReflectionPatterns(): Int = patterns.sumOf {
        val (rows, columns) = it.columnsAndRowWhenReflection()
        rows + 100 * columns
    }

    fun findReflectionPatternsWithChanges(): Int = patterns.sumOf {
        val (value, position) = it.columnsAndRowWhenReflectionWithChanges()
        when (position) {
            ReflectionPosition.ROW -> value
            ReflectionPosition.COLUMN -> value * 100
            ReflectionPosition.NONE -> 0
        }
    }

}