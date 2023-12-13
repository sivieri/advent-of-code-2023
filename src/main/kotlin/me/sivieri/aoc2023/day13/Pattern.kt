package me.sivieri.aoc2023.day13

import kotlin.math.min

class Pattern(data: String) {

    private val matrix = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size

    fun columnsLeftOfReflection(): Int =
        (0 until maxX)
            .zipWithNext()
            .map { pair ->
                var (left, right) = pair
                while (left >= 0 && right < maxX) {
                    val lc = matrix.map { it[left] }
                    val rc = matrix.map { it[right] }
                    if (lc != rc) return@map 0
                    left--
                    right++
                }
                pair.first + 1
            }
            .max()

    fun rowsAboveReflection(): Int =
        (0 until maxY)
            .zipWithNext()
            .map { pair ->
                var (up, down) = pair
                while (up >= 0 && down < maxY) {
                    val uc = matrix[up].toList()
                    val dc = matrix[down].toList()
                    if (uc != dc) return@map 0
                    up--
                    down++
                }
                pair.first + 1
            }
            .max()

}
