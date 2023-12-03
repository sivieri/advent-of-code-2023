package me.sivieri.aoc2023.day3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class EngineSchematicTest {

    @Test
    fun `part 1 example`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            .......755
            ...$..*...
            .664.598..
        """.trimIndent()
        val engineSchematic = EngineSchematic(input)
        val result = engineSchematic.sumParts()
        assertThat(result, `is`(4361))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            .......755
            ...$..*...
            .664.598..
        """.trimIndent()
        val engineSchematic = EngineSchematic(input)
        val result = engineSchematic.gearsRatio()
        assertThat(result, `is`(467835))
    }

}