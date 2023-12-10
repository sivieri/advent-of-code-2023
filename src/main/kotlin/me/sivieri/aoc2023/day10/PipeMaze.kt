package me.sivieri.aoc2023.day10

import me.sivieri.aoc2023.common.Coordinate2D

class PipeMaze(data: String, startSymbol: Char) {

    private val maze: MutableMap<Coordinate2D, PipeType>
    private val start: Coordinate2D
    private val maxX: Int
    private val maxY: Int

    init {
        var start = Coordinate2D(0, 0)
        val lines = data
            .split("\n")
            .filter { it.isNotBlank() }
        this.maxX = lines[0].length
        this.maxY = lines.size
        this.maze = lines
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    if (c == START) {
                        start = Coordinate2D(x, y)
                        Pair(Coordinate2D(x, y), PipeType.fromSymbol(startSymbol))
                    }
                    else Pair(Coordinate2D(x, y), PipeType.fromSymbol(c))
                }
            }.toMap().toMutableMap()
        this.start = start
    }

    fun findStepsFromStartToFarthest(): Int {
        val loop = findLoop()
        return loop.size / 2
    }

    private fun findLoop(): List<Coordinate2D> {
        val loop = mutableListOf(start)
        var direction = maze[start]!!.destinations().keys.first()
        var nextCoordinate = findNextCoordinate(start, direction)
        while (nextCoordinate != start) {
            loop.add(nextCoordinate)
            val type = maze[nextCoordinate]!!
            direction = type.nextDirection(direction.opposite())
            nextCoordinate = findNextCoordinate(nextCoordinate, direction)
        }
        return loop
    }

    companion object {
        private const val START = 'S'

        private fun findNextCoordinate(
            coordinate: Coordinate2D,
            direction: AnimalDirection
        ): Coordinate2D = when (direction) {
            AnimalDirection.UP -> Coordinate2D(coordinate.x, coordinate.y - 1)
            AnimalDirection.DOWN -> Coordinate2D(coordinate.x, coordinate.y + 1)
            AnimalDirection.LEFT -> Coordinate2D(coordinate.x - 1, coordinate.y)
            AnimalDirection.RIGHT -> Coordinate2D(coordinate.x + 1, coordinate.y)
        }
    }

}