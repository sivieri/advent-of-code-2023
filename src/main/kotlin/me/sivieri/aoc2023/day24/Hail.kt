package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Coordinate3DDouble
import me.sivieri.aoc2023.common.Coordinate3DLong
import me.sivieri.aoc2023.common.twoCombinations
import org.ejml.data.DMatrixRMaj
import org.ejml.simple.SimpleMatrix

class Hail(input: List<String>) {

    private val hailstones: List<Hailstone> = input.map { Hailstone.parse(it) }

    fun countIntersectingHailstones2D(min: Long, max: Long): Int = hailstones
        .twoCombinations()
        .count { (a, b) ->
            val pair = a.intersects2D(b)
            val future = a.intersectsInFuture2D(b)
            future && pair != null && pair.first >= min && pair.first <= max && pair.second >= min && pair.second <= max
        }

    fun findRockStartingPoint(): Long {
        val (one, two, three, four, five) = hailstones.subList(0, 5)
        val partialRock = findXY(one, two, three, four, five)
        val rock = findZ(partialRock, one, two)
        println("Rock: $rock")
        return (rock.position.x + rock.position.y + rock.position.z).toLong()
    }

    private fun findXY(
        one: Hailstone,
        two: Hailstone,
        three: Hailstone,
        four: Hailstone,
        five: Hailstone
    ): Rock {
        val a = arrayOf(
            arrayOf(
                (two.velocity.y - one.velocity.y).toDouble(),
                (one.velocity.x - two.velocity.x).toDouble(),
                (one.position.y - two.position.y).toDouble(),
                (two.position.x - one.position.x).toDouble()
            ).toDoubleArray(),
            arrayOf(
                (three.velocity.y - one.velocity.y).toDouble(),
                (one.velocity.x - three.velocity.x).toDouble(),
                (one.position.y - three.position.y).toDouble(),
                (three.position.x - one.position.x).toDouble()
            ).toDoubleArray(),
            arrayOf(
                (four.velocity.y - one.velocity.y).toDouble(),
                (one.velocity.x - four.velocity.x).toDouble(),
                (one.position.y - four.position.y).toDouble(),
                (four.position.x - one.position.x).toDouble()
            ).toDoubleArray(),
            arrayOf(
                (five.velocity.y - one.velocity.y).toDouble(),
                (one.velocity.x - five.velocity.x).toDouble(),
                (one.position.y - five.position.y).toDouble(),
                (five.position.x - one.position.x).toDouble()
            ).toDoubleArray(),
        )
        val b = arrayOf(
            (two.position.x * two.velocity.y - two.position.y * two.velocity.x - one.position.x * one.velocity.y + one.position.y * one.velocity.x).toDouble(),
            (three.position.x * three.velocity.y - three.position.y * three.velocity.x - one.position.x * one.velocity.y + one.position.y * one.velocity.x).toDouble(),
            (four.position.x * four.velocity.y - four.position.y * four.velocity.x - one.position.x * one.velocity.y + one.position.y * one.velocity.x).toDouble(),
            (five.position.x * five.velocity.y - five.position.y * five.velocity.x - one.position.x * one.velocity.y + one.position.y * one.velocity.x).toDouble()
        ).toDoubleArray()
        val am = SimpleMatrix.wrap(DMatrixRMaj(a))
        val bm = SimpleMatrix.wrap(DMatrixRMaj(b))
        val x = am.solve(bm)
        return Rock(
            Coordinate3DDouble(
                x.get(0, 0),
                x.get(1, 0),
                0.0
            ),
            Coordinate3DDouble(
                x.get(2, 0),
                x.get(3, 0),
                0.0
            )
        )
    }

    private fun findZ(
        rock: Rock,
        one: Hailstone,
        two: Hailstone
    ): Rock {
        val a = arrayOf(
            arrayOf(
                rock.velocity.x - one.velocity.x,
                one.position.x - rock.position.x
            ).toDoubleArray(),
            arrayOf(
                rock.velocity.x - two.velocity.x,
                two.position.x - rock.position.x
            ).toDoubleArray()
        )
        val b = arrayOf(
            -rock.position.x * one.velocity.z + one.position.x * one.velocity.z - one.position.z * one.velocity.x + one.position.z * rock.velocity.x,
            -rock.position.x * two.velocity.z + two.position.x * two.velocity.z - two.position.z * two.velocity.x + two.position.z * rock.velocity.x
        ).toDoubleArray()
        val am = SimpleMatrix.wrap(DMatrixRMaj(a))
        val bm = SimpleMatrix.wrap(DMatrixRMaj(b))
        val x = am.solve(bm)
        return Rock(
            rock.position.copy(z = x.get(0, 0)),
            rock.velocity.copy(z = x.get(1, 0))
        )
    }

}