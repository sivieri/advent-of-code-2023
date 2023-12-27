package me.sivieri.aoc2023.day22

import me.sivieri.aoc2023.common.Coordinate3D
import me.sivieri.aoc2023.common.tail
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BrickTest {

    @Test
    fun `distance single brick from basement`() {
        val brick = SandBrick(0, Coordinate3D(1, 0, 1), Coordinate3D(1, 2, 1))
        val basement = Basement(1, 1, 0, 2)
        val distance = brick.distance(basement.getCoordinates().toSet())
        assertThat(distance, `is`(1))
    }

    @Test
    fun `distance second brick from all`() {
        val brick = SandBrick(1, Coordinate3D(0, 0, 2), Coordinate3D(2, 0, 2))
        val coordinates = setOf(
            Coordinate3D(0, 0, 0),
            Coordinate3D(0, 1, 0),
            Coordinate3D(0, 2, 0),
            Coordinate3D(1, 0, 0),
            Coordinate3D(1, 1, 0),
            Coordinate3D(1, 2, 0),
            Coordinate3D(2, 0, 0),
            Coordinate3D(2, 1, 0),
            Coordinate3D(2, 2, 0),
            Coordinate3D(1, 0, 1),
            Coordinate3D(1, 1, 1),
            Coordinate3D(1, 2, 1)
        )
        val distance = brick.distance(coordinates)
        assertThat(distance, `is`(1))
    }

    @Test
    fun `distance third brick from second`() {
        val brick = SandBrick(1, Coordinate3D(0, 2, 3), Coordinate3D(2, 2, 3))
        val coordinates = setOf(
            Coordinate3D(0, 0, 0),
            Coordinate3D(0, 1, 0),
            Coordinate3D(0, 2, 0),
            Coordinate3D(1, 0, 0),
            Coordinate3D(1, 1, 0),
            Coordinate3D(1, 2, 0),
            Coordinate3D(2, 0, 0),
            Coordinate3D(2, 1, 0),
            Coordinate3D(2, 2, 0),
            Coordinate3D(1, 0, 1),
            Coordinate3D(1, 1, 1),
            Coordinate3D(1, 2, 1),
            Coordinate3D(0, 0, 2),
            Coordinate3D(1, 0, 2),
            Coordinate3D(2, 0, 2)
        )
        val distance = brick.distance(coordinates)
        assertThat(distance, `is`(2))
    }

    @Test
    fun `distance third brick from all with another layer above`() {
        val brick = SandBrick(1, Coordinate3D(0, 2, 3), Coordinate3D(2, 2, 3))
        val fauxBasement = Basement(0, 2, 0, 2, 3, 4)
        val coordinates = setOf(
            Coordinate3D(0, 0, 0),
            Coordinate3D(0, 1, 0),
            Coordinate3D(0, 2, 0),
            Coordinate3D(1, 0, 0),
            Coordinate3D(1, 1, 0),
            Coordinate3D(1, 2, 0),
            Coordinate3D(2, 0, 0),
            Coordinate3D(2, 1, 0),
            Coordinate3D(2, 2, 0),
            Coordinate3D(1, 0, 1),
            Coordinate3D(1, 1, 1),
            Coordinate3D(1, 2, 1),
            Coordinate3D(0, 0, 2),
            Coordinate3D(1, 0, 2),
            Coordinate3D(2, 0, 2)
        ) + fauxBasement.getCoordinates()
        val distance = brick.distance(coordinates)
        assertThat(distance, `is`(2))
    }

    @Test
    fun `supporting bricks`() {
        val bricks = listOf(
            SandBrick(0, Coordinate3D(1, 0, 1), Coordinate3D(1, 2, 1)),
            SandBrick(1, Coordinate3D(0, 0, 2), Coordinate3D(2, 0, 2)),
            SandBrick(2, Coordinate3D(0, 2, 2), Coordinate3D(2, 2, 2)),
            SandBrick(3, Coordinate3D(0, 0, 3), Coordinate3D(0, 2, 3)),
        )
        val supported = bricks.first().supportedBricks(bricks.tail())
        assertThat(supported, `is`(listOf(1, 2)))
    }

}