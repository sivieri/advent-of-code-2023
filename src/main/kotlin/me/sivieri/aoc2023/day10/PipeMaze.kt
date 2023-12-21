package me.sivieri.aoc2023.day10

import me.sivieri.aoc2023.common.Coordinate2D
import me.sivieri.aoc2023.common.toPolygon
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Polygon

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

    fun countInternalSpaces(): Int {
        val loop = findLoop()
        val transformed = maze.map { (c, p) ->
            if (c in loop) Pair(c, IN_LOOP)
            else if (p == PipeType.EMPTY) Pair(c, p.symbol)
            else Pair(c, NOT_LOOP)
        }.toMap()
        println(mazeToString(transformed, maxX, maxY))
        val polygon = loop.toPolygon()
        val contained = (0 until maxY).sumOf { y ->
            (0 until maxX)
                .filter { x -> Coordinate2D(x, y) !in loop }
                .count { x ->  polygon.contains(geometryFactory.createPoint(Coordinate(x.toDouble(), y.toDouble()))) }
        }
        return contained
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
        private const val IN_LOOP = 'X'
        private const val NOT_LOOP = 'o'
        private val geometryFactory = GeometryFactory()

        private fun findNextCoordinate(
            coordinate: Coordinate2D,
            direction: MazeDirection
        ): Coordinate2D = when (direction) {
            MazeDirection.UP -> Coordinate2D(coordinate.x, coordinate.y - 1)
            MazeDirection.DOWN -> Coordinate2D(coordinate.x, coordinate.y + 1)
            MazeDirection.LEFT -> Coordinate2D(coordinate.x - 1, coordinate.y)
            MazeDirection.RIGHT -> Coordinate2D(coordinate.x + 1, coordinate.y)
        }

        private fun mazeToString(
            maze: Map<Coordinate2D, Char>,
            maxX: Int,
            maxY: Int
        ): String = (0 until maxY).joinToString("\n") { y ->
            (0 until maxX).joinToString("") { x ->
                maze[Coordinate2D(x, y)]!!.toString()
            }
        }
    }

}