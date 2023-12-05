package me.sivieri.aoc2023.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.assertNull

class AlmanacRangesInclusiveTest {

    @Test
    fun `parsing a range`() {
        val line = "50 98 2"
        val range = AlmanacRangesInclusive.parse(line)
        val expected = AlmanacRangesInclusive(50, 51, 98, 99, 2)
        assertThat(range, `is`(expected))
    }

    @Test
    fun `source in range`() {
        val range = AlmanacRangesInclusive(50, 60, 98, 108, 11)
        assertThat(range.contains(103), `is`(true))
    }

    @Test
    fun `source out of range`() {
        val range = AlmanacRangesInclusive(50, 60, 98, 108, 11)
        assertThat(range.contains(23), `is`(false))
    }

    @Test
    fun `destination when in range`() {
        val range = AlmanacRangesInclusive(50, 60, 98, 108, 11)
        assertThat(range.findDestination(103), `is`(55))
    }

    @Test
    fun `destination out of range`() {
        val range = AlmanacRangesInclusive(50, 60, 98, 108, 11)
        assertNull(range.findDestination(23))
    }

}