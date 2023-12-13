package me.sivieri.aoc2023.day13

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class PatternTest {

    @Test
    fun `vertical reflection`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
        """.trimIndent()
        val pattern = Pattern(input)
        assertThat(pattern.columnsLeftOfReflection(), `is`(5))
    }

    @Test
    fun `horizontal reflection`() {
        val input = """
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()
        val pattern = Pattern(input)
        assertThat(pattern.rowsAboveReflection(), `is`(4))
    }

}