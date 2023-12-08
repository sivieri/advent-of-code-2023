package me.sivieri.aoc2023.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class DesertNavigatorTest {

    @Test
    fun `part 1 example 1`() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        val desertNavigator = DesertNavigator(input)
        val result = desertNavigator.countSteps()
        assertThat(result, `is`(2))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            LLR
            
            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        val desertNavigator = DesertNavigator(input)
        val result = desertNavigator.countSteps()
        assertThat(result, `is`(6))
    }

}