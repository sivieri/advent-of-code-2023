package me.sivieri.aoc2023.day14

import me.sivieri.aoc2023.common.stringRepresentation

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
        tilt(nuMatrix, DishTilt.NORTH)
        return calculateNorthWeight(nuMatrix)
    }

    private fun calculateNorthWeight(matrix: Array<Array<Char>>) = (0 until maxY).sumOf { y ->
        (maxY - y) * matrix[y].count { it == ROUND }
    }

    fun calculateLoad(iterations: Int): Int {
        val nuMatrix = (0 until maxY).map { y ->
            (0 until maxX).map { x -> matrix[y][x] }.toTypedArray()
        }.toTypedArray()
        tiltWithMemory(nuMatrix, iterations)
        return calculateNorthWeight(nuMatrix)
    }

    private fun tiltWithMemory(matrix: Array<Array<Char>>, iterations: Int) {
        val states = mutableListOf<String>()
        var i = 0L
        var repr = ""
        while (i < iterations) {
            tiltAllDirections(matrix)
            repr = matrix.stringRepresentation("") { it.toString() }
            if (repr in states) break
            states.add(repr)
            i++
        }
        if (i < iterations) println("Found loop after $i iterations")
        val index = states.indexOf(repr)
        val total = states.size
        val length = total - index
        val remaining = (iterations - index) % length
        (1 until remaining).forEach { _ -> tiltAllDirections(matrix) }
    }

    private fun tiltAllDirections(matrix: Array<Array<Char>>) {
        tilt(matrix, DishTilt.NORTH)
        tilt(matrix, DishTilt.WEST)
        tilt(matrix, DishTilt.SOUTH)
        tilt(matrix, DishTilt.EAST)
    }

    private fun tilt(matrix: Array<Array<Char>>, direction: DishTilt) {
        var changed = true
        while (changed) {
            changed = false
            when (direction) {
                DishTilt.NORTH -> {
                    (1 until maxY).forEach { y ->
                        (0 until maxX).forEach { x ->
                            if (matrix[y][x] == ROUND && matrix[y - 1][x] == EMPTY) {
                                changed = true
                                matrix[y - 1][x] = ROUND
                                matrix[y][x] = EMPTY
                            }
                        }
                    }
                }
                DishTilt.WEST -> {
                    (0 until maxY).forEach { y ->
                        (1 until maxX).forEach { x ->
                            if (matrix[y][x] == ROUND && matrix[y][x - 1] == EMPTY) {
                                changed = true
                                matrix[y][x - 1] = ROUND
                                matrix[y][x] = EMPTY
                            }
                        }
                    }
                }
                DishTilt.SOUTH -> {
                    (0 until (maxY - 1)).forEach { y ->
                        (0 until maxX).forEach { x ->
                            if (matrix[y][x] == ROUND && matrix[y + 1][x] == EMPTY) {
                                changed = true
                                matrix[y + 1][x] = ROUND
                                matrix[y][x] = EMPTY
                            }
                        }
                    }
                }
                DishTilt.EAST -> {
                    (0 until maxY).forEach { y ->
                        (0 until (maxX - 1)).forEach { x ->
                            if (matrix[y][x] == ROUND && matrix[y][x + 1] == EMPTY) {
                                changed = true
                                matrix[y][x + 1] = ROUND
                                matrix[y][x] = EMPTY
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val EMPTY = '.'
        private const val ROUND = 'O'
        private const val CUBE = '#'
    }

}