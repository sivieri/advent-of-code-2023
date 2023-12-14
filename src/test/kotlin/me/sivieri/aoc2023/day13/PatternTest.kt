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
        assertThat(pattern.columnsLeftOfReflection().first().value, `is`(5))
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
        assertThat(pattern.rowsAboveReflection().first().value, `is`(4))
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
        assertThat(pattern.rowsAboveReflection().first().value, `is`(3))
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

    @Test
    fun `example from input 1`() {
        val input = """
            .#.##.#.##..###
            ...##...#######
            #.####.#.#.###.
            #..##..##..#...
            ###..###....###
            .##..##..#.#...
            .#....#..######
            #..##..########
            ########.#..#..
        """.trimIndent()
        val pattern = Pattern(input)
        val result = pattern.columnsAndRowWhenReflectionWithChanges().value
        assertThat(result, `is`(14))
    }

}