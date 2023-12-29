package me.sivieri.aoc2023.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3DDouble(
    val x: Double,
    val y: Double,
    val z: Double
): Comparable<Coordinate3DDouble> {

    override fun compareTo(other: Coordinate3DDouble): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3DDouble): Double =
        kotlin.math.sqrt((other.x - x).pow(2) + (other.y - y).pow(2) + (other.y - y).pow(2))

    fun manhattanDistance(other: Coordinate3DDouble): Double =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3DDouble): Coordinate3DDouble =
        Coordinate3DDouble(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3DDouble(0.0, 0.0, 0.0)

        fun parseString(s: String, separator: String = ","): Coordinate3DDouble {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3DDouble(x.toDouble(), y.toDouble(), z.toDouble())
        }

    }

}
