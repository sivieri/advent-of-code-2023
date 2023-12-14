package me.sivieri.aoc2023.day14

class Dish(data: String) {

    private val matrix = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size

    fun calculateLoadTiltedNorth(): Int {
        val nuMatrix = (0 until maxY).map { y ->
            (0 until maxX).map { x -> matrix[y][x] }.toTypedArray()
        }.toTypedArray()
        var changed = true
        while (changed) {
            changed = false
            (1 until maxY).forEach { y ->
                (0 until maxX).forEach { x ->
                    if (nuMatrix[y][x] == ROUND && nuMatrix[y - 1][x] == EMPTY) {
                        changed = true
                        nuMatrix[y - 1][x] = ROUND
                        nuMatrix[y][x] = EMPTY
                    }
                }
            }
        }
        return (0 until maxY).sumOf { y ->
            (maxY - y) * nuMatrix[y].count { it == ROUND }
        }
    }

    companion object {
        private const val EMPTY = '.'
        private const val ROUND = 'O'
        private const val CUBE = '#'
    }

}