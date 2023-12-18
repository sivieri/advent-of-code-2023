package me.sivieri.aoc2023.day15

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class HolidayHashTest {

    @Test
    fun `hash HASH`() {
        val hash = HolidayHash.hash("HASH")
        assertThat(hash, `is`(52))
    }

}