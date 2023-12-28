package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Coordinate3DLong
import org.apache.commons.geometry.euclidean.twod.Line
import org.apache.commons.geometry.euclidean.twod.Lines
import org.apache.commons.geometry.euclidean.twod.Vector2D
import org.apache.commons.numbers.core.Precision
import kotlin.math.sign

data class Hailstone(
    val position: Coordinate3DLong,
    val velocity: Coordinate3DLong
) {

    fun intersects2D(other: Hailstone): Pair<Double, Double>? {
        val a = toLine()
        val b = other.toLine()
        val v = a.intersection(b)
        return if (v != null) Pair(v.x, v.y)
        else null
    }

    fun intersectsInFuture(other: Hailstone): Boolean {
        val intersection = intersects2D(other) ?: return false
        val diffX1 = intersection.first - position.x
        val diffX2 = intersection.first - other.position.x
        val diffY1 = intersection.second - position.y
        val diffY2 = intersection.second - other.position.y
        return sign(diffX1) == sign(velocity.x.toDouble()) &&
            sign(diffX2) == sign(other.velocity.x.toDouble()) &&
            sign(diffY1) == sign(velocity.y.toDouble()) &&
            sign(diffY2) == sign(other.velocity.y.toDouble())
    }

    private fun toLine(): Line {
        val pointAtZero = Vector2D.of(position.x.toDouble(), position.y.toDouble())
        val direction = Vector2D.of(velocity.x.toDouble(), velocity.y.toDouble())
        return Lines.fromPointAndDirection(pointAtZero, direction, precision)
    }

    companion object {
        private val precision = Precision.doubleEquivalenceOfEpsilon(1e-6)

        fun parse(s: String): Hailstone {
            val (position, velocity) = s.split(" @ ", limit = 2)
            val (px, py, pz) = position.split(",", limit = 3)
            val (vx, vy, vz) = velocity.split(",", limit = 3)
            return Hailstone(
                Coordinate3DLong(
                    px.trim().toLong(),
                    py.trim().toLong(),
                    pz.trim().toLong()
                ),
                Coordinate3DLong(
                    vx.trim().toLong(),
                    vy.trim().toLong(),
                    vz.trim().toLong()
                )
            )
        }
    }
}
