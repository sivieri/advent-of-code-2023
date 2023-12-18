package me.sivieri.aoc2023.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class DishTest {

    @Test
    fun `part 1 example`() {
        val input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent()
        val dish = Dish(input)
        val result = dish.calculateLoadTiltedNorth()
        assertThat(result, `is`(136))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent()
        val dish = Dish(input)
        val result = dish.calculateLoad(1_000_000_000)
        assertThat(result, `is`(64))
    }

}