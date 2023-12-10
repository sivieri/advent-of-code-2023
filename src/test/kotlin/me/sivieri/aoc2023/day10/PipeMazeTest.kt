package me.sivieri.aoc2023.day10

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class PipeMazeTest {

    @Test
    fun `part 1 example 1`() {
        val input = """
            -L|F7
            7S-7|
            L|7||
            -L-J|
            L|-JF
        """.trimIndent()
        val pipeMaze = PipeMaze(input, 'F')
        val steps = pipeMaze.findStepsFromStartToFarthest()
        assertThat(steps, `is`(4))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
        """.trimIndent()
        val pipeMaze = PipeMaze(input, 'F')
        val steps = pipeMaze.findStepsFromStartToFarthest()
        assertThat(steps, `is`(8))
    }

}