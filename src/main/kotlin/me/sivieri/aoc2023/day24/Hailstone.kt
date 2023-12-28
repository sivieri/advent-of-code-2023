package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Coordinate3DLong
import org.apache.commons.geometry.euclidean.threed.Vector3D
import org.apache.commons.geometry.euclidean.threed.line.Line3D
import org.apache.commons.geometry.euclidean.threed.line.Lines3D
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
        val a = toLine2D()
        val b = other.toLine2D()
        val v = a.intersection(b)
        return if (v != null) Pair(v.x, v.y)
        else null
    }

    fun intersectsInFuture2D(other: Hailstone): Boolean {
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

    private fun toLine2D(): Line {
        val pointAtZero = Vector2D.of(position.x.toDouble(), position.y.toDouble())
        val direction = Vector2D.of(velocity.x.toDouble(), velocity.y.toDouble())
        return Lines.fromPointAndDirection(pointAtZero, direction, precision)
    }

    private fun toLine3D(): Line3D {
        val pointAtZero = Vector3D.of(position.x.toDouble(), position.y.toDouble(), position.z.toDouble())
        val direction = Vector3D.of(velocity.x.toDouble(), velocity.y.toDouble(), velocity.z.toDouble())
        return Lines3D.fromPointAndDirection(pointAtZero, direction, precision)
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
