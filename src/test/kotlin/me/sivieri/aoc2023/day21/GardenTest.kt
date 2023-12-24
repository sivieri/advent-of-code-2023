package me.sivieri.aoc2023.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class GardenTest {

    @Test
    fun `part 1 example pre 1`() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent()
        val garden = Garden(input)
        val covered = garden.coveredAreas(1)
        assertThat(covered, `is`(2))
    }

    @Test
    fun `part 1 example pre 2`() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent()
        val garden = Garden(input)
        val covered = garden.coveredAreas(2)
        assertThat(covered, `is`(4))
    }

    @Test
    fun `part 1 example pre 3`() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent()
        val garden = Garden(input)
        val covered = garden.coveredAreas(3)
        assertThat(covered, `is`(6))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent()
        val garden = Garden(input)
        val covered = garden.coveredAreas(6)
        assertThat(covered, `is`(16))
    }

}