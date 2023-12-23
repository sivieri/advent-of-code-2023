package me.sivieri.aoc2023.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class PathTest {

    @Test
    fun `test values`() {
        val path = Path("A", listOf(
            GreaterThanOrEqualsCondition('a', 2006),
            LessThanCondition('a', 2090)
        ), "px")
        val values = path.findValidValues('a')
        val expected = (2006 until 2090).toList()
        assertThat(values, `is`(expected))
    }

}