package me.sivieri.aoc2023.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BoatRacerTest {

    @Test
    fun `part 1 example`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        val racer = BoatRacer(input)
        val ways = racer.countWaysToWin()
        assertThat(ways, `is`(288))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        val racer = BoatRacer(input)
        val ways = racer.countSingleRace()
        assertThat(ways, `is`(71503))
    }

}