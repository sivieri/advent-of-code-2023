package me.sivieri.aoc2023.day13

import me.sivieri.aoc2023.common.sum
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
        assertThat(pattern.columnsLeftOfReflection().first, `is`(5))
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
        assertThat(pattern.rowsAboveReflection().first, `is`(4))
    }

    @Test
    fun `another horizontal reflection`() {
        val input = """
            ..##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
        """.trimIndent()
        val pattern = Pattern(input)
        assertThat(pattern.rowsAboveReflection().first, `is`(3))
    }

    @Test
    fun `new horizontal reflection when changed`() {
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
        val result = pattern.columnsAndRowWhenReflectionWithChanges().value
        assertThat(result, `is`(3))
    }

    @Test
    fun `old horizontal reflection when changed`() {
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
        val result = pattern.columnsAndRowWhenReflectionWithChanges().value
        assertThat(result, `is`(1))
    }

}