package me.sivieri.aoc2023.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BoatRaceTest {

    @Test
    fun `example 1`() {
        val race = BoatRace(7, 9)
        val ways = race.countWaysToWin()
        assertThat(ways, `is`(4))
    }

    @Test
    fun `example 2`() {
        val race = BoatRace(15, 40)
        val ways = race.countWaysToWin()
        assertThat(ways, `is`(8))
    }

    @Test
    fun `example 3`() {
        val race = BoatRace(30, 200)
        val ways = race.countWaysToWin()
        assertThat(ways, `is`(9))
    }

}