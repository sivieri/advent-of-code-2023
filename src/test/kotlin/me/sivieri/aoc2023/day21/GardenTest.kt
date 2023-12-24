package me.sivieri.aoc2023.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
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

    @Test
    @Ignore
    fun `part 2 example 1`() {
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
        val covered = garden.coveredAreasWithExtension(6)
        assertThat(covered, `is`(16))
    }

    @Test
    @Ignore
    fun `part 2 example 2`() {
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
        val covered = garden.coveredAreasWithExtension(10)
        assertThat(covered, `is`(50))
    }

    @Test
    @Ignore
    fun `part 2 example 3`() {
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
        val covered = garden.coveredAreasWithExtension(50)
        assertThat(covered, `is`(1594))
    }

    @Test
    @Ignore
    fun `part 2 example 4`() {
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
        val covered = garden.coveredAreasWithExtension(100)
        assertThat(covered, `is`(6536))
    }

    @Test
    @Ignore
    fun `part 2 example 5`() {
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
        val covered = garden.coveredAreasWithExtension(500)
        assertThat(covered, `is`(167004))
    }

    @Test
    @Ignore
    fun `part 2 example 6`() {
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
        val covered = garden.coveredAreasWithExtension(1000)
        assertThat(covered, `is`(668697))
    }

    @Test
    @Ignore
    fun `part 2 example 7`() {
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
        val covered = garden.coveredAreasWithExtension(5000)
        assertThat(covered, `is`(16733044))
    }

}