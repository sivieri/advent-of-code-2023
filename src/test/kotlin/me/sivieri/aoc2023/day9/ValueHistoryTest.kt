package me.sivieri.aoc2023.day9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ValueHistoryTest {

    @Test
    fun `line 1 ff`() {
        val history = ValueHistory("0 3 6 9 12 15".split(" ").map { it.toInt() })
        val result = history.findNextValue()
        assertThat(result, `is`(18))
    }

    @Test
    fun `line 2 ff`() {
        val history = ValueHistory("1 3 6 10 15 21".split(" ").map { it.toInt() })
        val result = history.findNextValue()
        assertThat(result, `is`(28))
    }

    @Test
    fun `line 3 ff`() {
        val history = ValueHistory("10 13 16 21 30 45".split(" ").map { it.toInt() })
        val result = history.findNextValue()
        assertThat(result, `is`(68))
    }

    @Test
    fun `line 1 bw`() {
        val history = ValueHistory("0 3 6 9 12 15".split(" ").map { it.toInt() })
        val result = history.findPrevValue()
        assertThat(result, `is`(-3))
    }

    @Test
    fun `line 2 bw`() {
        val history = ValueHistory("1 3 6 10 15 21".split(" ").map { it.toInt() })
        val result = history.findPrevValue()
        assertThat(result, `is`(0))
    }

    @Test
    fun `line 3 bw`() {
        val history = ValueHistory("10 13 16 21 30 45".split(" ").map { it.toInt() })
        val result = history.findPrevValue()
        assertThat(result, `is`(5))
    }

}