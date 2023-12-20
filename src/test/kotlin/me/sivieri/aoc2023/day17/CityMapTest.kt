package me.sivieri.aoc2023.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class CityMapTest {

    @Test
    fun `part 1 example`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent()
        val map = CityMap(input)
        val loss = map.calculateMinHeatLoss()
        assertThat(loss, `is`(102))
    }

    @Test
    @Ignore
    fun `part 2 example`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent()
        val map = CityMap(input)
        val loss = map.calculateUltraMinHeatLoss()
        assertThat(loss, `is`(94))
    }

}