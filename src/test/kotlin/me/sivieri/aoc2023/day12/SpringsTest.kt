package me.sivieri.aoc2023.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class SpringsTest {

    @Test
    fun `part 1 example`() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent().split("\n")
        val springs = Springs(input)
        val result = springs.findAllArrangements()
        assertThat(result, `is`(21))
    }

    @Test
    @Ignore
    fun `part 2 example`() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent().split("\n")
        val springs = Springs(input)
        val result = springs.findAllArrangements(extended = true)
        assertThat(result, `is`(525152))
    }

}