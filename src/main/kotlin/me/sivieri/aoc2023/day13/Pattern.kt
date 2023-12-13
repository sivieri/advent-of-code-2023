package me.sivieri.aoc2023.day13

import me.sivieri.aoc2023.common.sum

class Pattern(data: String) {

    private val matrix = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size

    fun columnsAndRowWhenReflection(): Pair<Int, Int> {
        val standard = Pair(columnsLeftOfReflection(), rowsAboveReflection())
        return Pair(standard.first.first, standard.second.first)
    }

    fun columnsAndRowWhenReflectionWithChanges(): ReflectionResult {
        val standard = Pair(columnsLeftOfReflection(), rowsAboveReflection())
        var y = 0
        while (y < maxY) {
            var x = 0
            while (x < maxX) {
                if (matrix[y][x] == ASH) {
                    matrix[y][x] = ROCK
                    val (columns, rows) = Pair(columnsLeftOfReflection(), rowsAboveReflection())
                    matrix[y][x] = ASH
                    val possibility = Pair(columns.first, rows.first)
                    if (possibility != Pair(0, 0) &&
                        ((x != columns.second && possibility.first != standard.first.first) ||
                            (y != rows.second && possibility.second != standard.second.first)
                            )
                    ) {
                        return if (possibility.first != standard.first.first) ReflectionResult(possibility.first, ReflectionPosition.ROW)
                        else ReflectionResult(possibility.second, ReflectionPosition.COLUMN)
                    }
                }
                else {
                    matrix[y][x] = ASH
                    val (columns, rows) = Pair(columnsLeftOfReflection(), rowsAboveReflection())
                    matrix[y][x] = ROCK
                    val possibility = Pair(columns.first, rows.first)
                    if (possibility != Pair(0, 0) &&
                        ((x != columns.second && possibility.first != standard.first.first) ||
                            (y != rows.second && possibility.second != standard.second.first)
                            )
                    ) {
                        return if (possibility.first != standard.first.first) ReflectionResult(possibility.first, ReflectionPosition.ROW)
                        else ReflectionResult(possibility.second, ReflectionPosition.COLUMN)
                    }
                }
                x++
            }
            y++
        }
        println("No possibility found for matrix\n${matrixRepresentation()}")
        return ReflectionResult(0, ReflectionPosition.NONE)
    }

    fun columnsLeftOfReflection(): Pair<Int, Int> = (0 until maxX)
        .zipWithNext()
        .map { pair ->
            var (left, right) = pair
            while (left >= 0 && right < maxX) {
                val lc = matrix.map { it[left] }
                val rc = matrix.map { it[right] }
                if (lc != rc) return@map Pair(0, 0)
                left--
                right++
            }
            val remaining = if (left < 0) right
            else left
            Pair(pair.first + 1, remaining)
        }
        .maxBy { it.first }

    fun rowsAboveReflection(): Pair<Int, Int> = (0 until maxY)
        .zipWithNext()
        .map { pair ->
            var (up, down) = pair
            while (up >= 0 && down < maxY) {
                val uc = matrix[up].toList()
                val dc = matrix[down].toList()
                if (uc != dc) return@map Pair(0, 0)
                up--
                down++
            }
            val remaining = if (up < 0) down
            else up
            Pair(pair.first + 1, remaining)
        }
        .maxBy { it.first }

    private fun matrixRepresentation(): String = matrix.joinToString("\n") { it.joinToString("") }

    companion object {
        private const val ASH = '.'
        private const val ROCK = '#'
    }

}
