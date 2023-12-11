package me.sivieri.aoc2023.day11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class SpaceTest {

    @Test
    fun `sample path 1`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
        val space = Space.fromString(input)
        val five = space.galaxies.find { it.index == 5 }!!
        val nine = space.galaxies.find { it.index == 9 }!!
        val length = space.shortestPathLength(five, nine)
        assertThat(length, `is`(9))
    }

    @Test
    fun `sample path 2`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
        val space = Space.fromString(input)
        val one = space.galaxies.find { it.index == 1 }!!
        val seven = space.galaxies.find { it.index == 7 }!!
        val length = space.shortestPathLength(one, seven)
        assertThat(length, `is`(15))
    }

    @Test
    fun `sample path 4`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
        val space = Space.fromString(input)
        val three = space.galaxies.find { it.index == 3 }!!
        val six = space.galaxies.find { it.index == 6 }!!
        val length = space.shortestPathLength(three, six)
        assertThat(length, `is`(17))
    }

    @Test
    fun `sample path 3`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()
        val space = Space.fromString(input)
        val eight = space.galaxies.find { it.index == 8 }!!
        val nine = space.galaxies.find { it.index == 9 }!!
        val length = space.shortestPathLength(eight, nine)
        assertThat(length, `is`(5))
    }

}