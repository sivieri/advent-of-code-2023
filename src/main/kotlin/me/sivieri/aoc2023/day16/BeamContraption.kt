package me.sivieri.aoc2023.day16

import me.sivieri.aoc2023.common.Coordinate2D
import java.lang.IllegalArgumentException

class BeamContraption(input: String) {

    private val matrix = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.map { Mirror.fromSymbol(it) }.toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size

    fun countMaxEnergizedCells(): Int = (0 until maxX)
        .flatMap { x ->
            (0 until maxY).mapNotNull { y ->
                if (x == 0 || x == (maxX - 1) || y == 0 || y == (maxY - 1)) Coordinate2D(x, y)
                else null
            }
        }
        .maxOf { c ->
            val direction = when {
                c.x == 0 -> BeamDirection.RIGHT
                c.x == maxX - 1 -> BeamDirection.LEFT
                c.y == 0 -> BeamDirection.DOWN
                c.y == maxY - 1 -> BeamDirection.UP
                else -> throw IllegalArgumentException("Impossible coordinate $c")
            }
            println("Trying coordinate $c")
            countEnergizedCells(Beam(c, direction))
        }

    fun countEnergizedCells(startingPoint: Beam = Beam(Coordinate2D(0, 0), BeamDirection.RIGHT)): Int {
        val energized = MutableSetWithStability<Coordinate2D>(maxX * maxY)
        val queue = ArrayDeque(listOf(startingPoint))
        while (queue.isNotEmpty()) {
            val beam = queue.removeFirst()
            var c: Coordinate2D? = beam.position
            while (c != null) {
                energized.add(c.copy())
                if (energized.isStable()) break
                val possible = when (beam.direction) {
                    BeamDirection.UP -> {
                        if (c.y - 1 >= 0) Coordinate2D(c.x, c.y - 1)
                        else null
                    }
                    BeamDirection.LEFT -> {
                        if (c.x - 1 >= 0) Coordinate2D(c.x - 1, c.y)
                        else null
                    }
                    BeamDirection.DOWN -> {
                        if (c.y + 1 < maxY) Coordinate2D(c.x, c.y + 1)
                        else null
                    }
                    BeamDirection.RIGHT -> {
                        if (c.x + 1 < maxX) Coordinate2D(c.x + 1, c.y)
                        else null
                    }
                }
                c = when (matrix[c.y][c.x]) {
                    Mirror.EMPTY -> possible
                    Mirror.HORIZONTAL_SPLITTER -> {
                        if (beam.direction == BeamDirection.UP || beam.direction == BeamDirection.DOWN) {
                            val left = Coordinate2D(c.x - 1, c.y)
                            val right = Coordinate2D(c.x + 1, c.y)
                            queue.addWithCheck(left, BeamDirection.LEFT)
                            queue.addWithCheck(right, BeamDirection.RIGHT)
                            null
                        }
                        else possible
                    }
                    Mirror.VERTICAL_SPLITTER -> {
                        if (beam.direction == BeamDirection.LEFT || beam.direction == BeamDirection.RIGHT) {
                            val up = Coordinate2D(c.x, c.y - 1)
                            val down = Coordinate2D(c.x, c.y + 1)
                            queue.addWithCheck(up, BeamDirection.UP)
                            queue.addWithCheck(down, BeamDirection.DOWN)
                            null
                        }
                        else possible
                    }
                    Mirror.LEFT_ANGLE -> {
                        when (beam.direction) {
                            BeamDirection.UP -> {
                                val right = Coordinate2D(c.x + 1, c.y)
                                queue.addWithCheck(right, BeamDirection.RIGHT)
                            }
                            BeamDirection.LEFT -> {
                                val down = Coordinate2D(c.x, c.y + 1)
                                queue.addWithCheck(down, BeamDirection.DOWN)
                            }
                            BeamDirection.DOWN -> {
                                val left = Coordinate2D(c.x - 1, c.y)
                                queue.addWithCheck(left, BeamDirection.LEFT)

                            }
                            BeamDirection.RIGHT -> {
                                val up = Coordinate2D(c.x, c.y - 1)
                                queue.addWithCheck(up, BeamDirection.UP)
                            }
                        }
                        null
                    }
                    Mirror.RIGHT_ANGLE -> {
                        when (beam.direction) {
                            BeamDirection.UP -> {
                                val left = Coordinate2D(c.x - 1, c.y)
                                queue.addWithCheck(left, BeamDirection.LEFT)
                            }
                            BeamDirection.LEFT -> {
                                val up = Coordinate2D(c.x, c.y - 1)
                                queue.addWithCheck(up, BeamDirection.UP)
                            }
                            BeamDirection.DOWN -> {
                                val right = Coordinate2D(c.x + 1, c.y)
                                queue.addWithCheck(right, BeamDirection.RIGHT)

                            }
                            BeamDirection.RIGHT -> {
                                val down = Coordinate2D(c.x, c.y + 1)
                                queue.addWithCheck(down, BeamDirection.DOWN)
                            }
                        }
                        null
                    }
                }
            }
        }
        return energized.size
    }

    private fun ArrayDeque<Beam>.addWithCheck(c: Coordinate2D, d: BeamDirection) {
        if (c.x in 0 until maxX && c.y in 0 until maxY) this.add(Beam(c, d))
    }

}