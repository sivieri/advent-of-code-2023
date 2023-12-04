package me.sivieri.aoc2023.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ScratchcardTest {

    @Test
    fun `parse a line`() {
        val line = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        val parsed = Scratchcard.parse(line)
        val expected = Scratchcard(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `points greater than zero`() {
        val card = Scratchcard(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))
        assertThat(card.points(), `is`(8))
    }

    @Test
    fun `zero points`() {
        val card = Scratchcard(6, setOf(31, 18, 13, 56, 72),  setOf(74, 77, 10, 23, 35, 67, 36, 11))
        assertThat(card.points(), `is`(0))
    }

}