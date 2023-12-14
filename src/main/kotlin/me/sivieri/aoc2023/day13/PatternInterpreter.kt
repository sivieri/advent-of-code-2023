package me.sivieri.aoc2023.day13

class PatternInterpreter(data: String) {

    private val patterns = data.split("\n\n").map { Pattern(it) }

    fun findReflectionPatterns(): Int = patterns.sumOf {
        val (col, row) = it.columnsAndRowWhenReflection()
        (col?.value ?: 0) + 100 * (row?.value ?: 0)
    }

    fun findReflectionPatternsWithChanges(): Int = patterns.sumOf {
        val r = it.columnsAndRowWhenReflectionWithChanges()
        when (r.position) {
            ReflectionPosition.ROW -> 100 * r.value
            ReflectionPosition.COLUMN -> r.value
            ReflectionPosition.NONE -> 0
        }
    }

}