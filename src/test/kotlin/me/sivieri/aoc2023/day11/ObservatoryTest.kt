package me.sivieri.aoc2023.day11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ObservatoryTest {

    @Test
    fun `part 1 example`() {
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
        val observatory = Observatory(input)
        val result = observatory.sumShortestPaths()
        assertThat(result, `is`(374))
    }

}