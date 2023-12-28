package me.sivieri.aoc2023.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3DLong(
    val x: Long,
    val y: Long,
    val z: Long
): Comparable<Coordinate3DLong> {

    override fun compareTo(other: Coordinate3DLong): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3DLong): Long =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toLong()

    fun manhattanDistance(other: Coordinate3DLong): Long =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3DLong): Coordinate3DLong =
        Coordinate3DLong(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3DLong(0, 0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate3DLong {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3DLong(x.toLong(), y.toLong(), z.toLong())
        }

    }

}
