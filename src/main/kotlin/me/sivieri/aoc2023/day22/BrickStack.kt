package me.sivieri.aoc2023.day22

import me.sivieri.aoc2023.common.foldIndexed
import kotlin.math.min

class BrickStack(data: List<String>) {

    private val bricks: List<Brick> = data
        .mapIndexed { i, s -> SandBrick.parse(s, i) }
        .sortedBy { min(it.end1.z, it.end2.z) }
    private val limitCoordinates = bricks
        .flatMap { (it as SandBrick).let { listOf(it.end1, it.end2) } }
        .toSet()
    private val minX = limitCoordinates.minOf { it.x }
    private val maxX = limitCoordinates.maxOf { it.x }
    private val minY = limitCoordinates.minOf { it.y }
    private val maxY = limitCoordinates.maxOf { it.y }
    private val minZ = limitCoordinates.minOf { it.z }
    private val maxZ = limitCoordinates.maxOf { it.z }

    fun countDisposableBricks(): Int {
        val settled = settle()
        TODO()
    }

    private fun settle(): List<Brick> {
        val basement: Brick = Basement(minX, maxX, minY, maxY, minZ, maxZ)
        val coordinates = bricks
            .flatMap { it.getCoordinates() }
            .toSet()
            .toMutableSet()
        coordinates.addAll(basement.getCoordinates())
        return bricks.foldIndexed(emptyList()) { acc, i, brick ->
            val d = brick.distance(coordinates)
            val res = if (d > 1) brick.bringDown(d - 1)
            else brick
            coordinates.clear()
            coordinates.addAll(acc.flatMap { it.getCoordinates() })
            coordinates.addAll(res.getCoordinates())
            coordinates.addAll(bricks.subList(i + 1, bricks.size).flatMap { it.getCoordinates() })
            acc.plus(res)
        }
    }

}