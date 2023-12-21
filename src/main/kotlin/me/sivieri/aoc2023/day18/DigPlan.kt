package me.sivieri.aoc2023.day18

import me.sivieri.aoc2023.common.Coordinate2D
import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.common.stringRepresentation
import me.sivieri.aoc2023.common.toPolygon
import me.sivieri.aoc2023.day10.PipeMaze
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

class DigPlan(input: List<String>) {

    private val instructions = input.map { DigInstruction.fromString(it) }

    fun countDigSpace(): Int {
        val coordinates = mutableListOf<Coordinate2D>()
        var cur = Coordinate2D(0, 0)
        coordinates.add(cur)
        instructions.forEach { i ->
            (1..i.meters).forEach { _ ->
                cur = when (i.direction) {
                    Direction.UP -> Coordinate2D(cur.x, cur.y - 1)
                    Direction.DOWN -> Coordinate2D(cur.x, cur.y + 1)
                    Direction.LEFT -> Coordinate2D(cur.x - 1, cur.y)
                    Direction.RIGHT -> Coordinate2D(cur.x + 1, cur.y)
                }
                coordinates.add(cur)
            }
        }
        val minX = coordinates.minOf { it.x }
        val minY = coordinates.minOf { it.y }
        val final = coordinates.map { Coordinate2D(it.x - minX, it.y - minY) }
        val maxX = final.maxOf { it.x }
        val maxY = final.maxOf { it.y }
        val site = Array(maxY + 1) { Array(maxX + 1) { EMPTY } }
        (0..maxY).forEach { y ->
            (0..maxX).forEach { x ->
                val c = Coordinate2D(x, y)
                if (c in final) site[y][x] = DUG
            }
        }
        val polygon = final.toPolygon()
        (0..maxY).forEach { y ->
            (0..maxX).forEach{ x ->
                if (polygon.contains(geometryFactory.createPoint(Coordinate(x.toDouble(), y.toDouble()))))
                    site[y][x] = DUG
            }
        }
        return site.sumOf { it.count { it == DUG } }
    }

    companion object {
        private const val EMPTY = '.'
        private const val DUG = '#'

        private val geometryFactory = GeometryFactory()
    }

}