package me.sivieri.aoc2023.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RuleTest {

    @Test
    fun `parse gt`() {
        val s = "x>10:A"
        val r = GreaterThanRule('x', 10, "A")
        assertThat(Rule.parse(s), `is`(r))
    }

    @Test
    fun `parse lt`() {
        val s = "x<10:A"
        val r = LessThanRule('x', 10, "A")
        assertThat(Rule.parse(s), `is`(r))
    }

    @Test
    fun `parse destination`() {
        val s = "A"
        val r = DestinationRule("A")
        assertThat(Rule.parse(s), `is`(r))
    }

}