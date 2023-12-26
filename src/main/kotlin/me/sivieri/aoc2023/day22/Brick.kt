package me.sivieri.aoc2023.day22

import me.sivieri.aoc2023.common.Coordinate3D
import me.sivieri.aoc2023.common.crossProduct
import me.sivieri.aoc2023.common.rangeBetween
import kotlin.math.abs

sealed class Brick {
    abstract fun getCoordinates(): List<Coordinate3D>

    fun distance(coordinates: Set<Coordinate3D>): Int {
        val these = getCoordinates()
        val all = coordinates + these
        val xes = all.map { it.x }.distinct()
        val yes = all.map { it.y }.distinct()
        return xes.crossProduct(yes).minOf { (x, y) ->
            val z1 = these.find { it.x == x && it.y == y }?.z
            val z2 = coordinates.find { it.x == x && it.y == y }?.z
            if (z1 != null && z2 != null) abs(z2 - z1)
            else Int.MAX_VALUE
        }
    }

    abstract fun bringDown(distance: Int): Brick
}

data class SandBrick(
    val id: Int,
    val end1: Coordinate3D,
    val end2: Coordinate3D
): Brick() {

    override fun getCoordinates(): List<Coordinate3D> = when {
        end1.x != end2.x -> rangeBetween(end1.x, end2.x).map { Coordinate3D(it, end1.y, end1.z) }
        end1.y != end2.y -> rangeBetween(end1.y, end2.y).map { Coordinate3D(end1.x, it, end1.z) }
        end1.z != end2.z -> rangeBetween(end1.z, end2.z).map { Coordinate3D(end1.x, end1.y, it) }
        else -> throw IllegalStateException("Cannot happen")
    }

    override fun bringDown(distance: Int): Brick = SandBrick(
        id,
        end1.copy(z = end1.z - distance),
        end2.copy(z = end2.z - distance)
    )

    companion object {
        fun parse(s: String, id: Int): SandBrick {
            val (e1, e2) = s.split("~", limit = 2)
            return SandBrick(id, Coordinate3D.parseString(e1), Coordinate3D.parseString(e2))
        }
    }

}

data class Basement(
    val minX: Int,
    val maxX: Int,
    val minY: Int,
    val maxY: Int,
    val minZ: Int,
    val maxZ: Int
): Brick() {

    override fun getCoordinates(): List<Coordinate3D> = (minX..maxX)
        .flatMap { x ->
            (minY..maxY).flatMap { y ->
                (minZ..maxZ).map { z -> Coordinate3D(x, y, z) }
            }
        }

    override fun bringDown(distance: Int): Brick = throw NotImplementedError("Not needed here")

}
