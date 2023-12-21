package me.sivieri.aoc2023.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class MachinePartTest {

    @Test
    fun parse() {
        val s = "{x=122,m=847,a=2434,s=3}"
        val expected = MachinePart(122, 847, 2434, 3)
        assertThat(MachinePart.parse(s), `is`(expected))
    }

    @Test
    fun rating() {
        val p = MachinePart(122, 847, 2434, 3)
        assertThat(p.rating(), `is`(122 + 847 + 2434 + 3))
    }

}