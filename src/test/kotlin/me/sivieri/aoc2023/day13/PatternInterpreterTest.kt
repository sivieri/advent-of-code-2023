package me.sivieri.aoc2023.day13

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class PatternInterpreterTest {

    @Test
    fun `part 1 example`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()
        val patternInterpreter = PatternInterpreter(input)
        val result = patternInterpreter.findReflectionPatterns()
        assertThat(result, `is`(405))
    }

}