package me.sivieri.aoc2023.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.assertNull

class ModuleTest {

    @Test
    fun `debug module`() {
        val m = DebugModule("output")
        assertNull(m.response("a", Pulse.HIGH))
    }

    @Test
    fun `broadcast module`() {
        val m = BroadcastModule(listOf("a", "b", "c"))
        assertThat(m.response("a", Pulse.LOW), `is`(Pulse.LOW))
        assertThat(m.response("b", Pulse.HIGH), `is`(Pulse.HIGH))
    }

    @Test
    fun `flip-flop sequence`() {
        val m = FlipFlopModule("a", listOf("c"))
        assertNull(m.response("b", Pulse.HIGH))
        assertThat(m.response("b", Pulse.LOW), `is`(Pulse.HIGH))
        assertThat(m.response("b", Pulse.LOW), `is`(Pulse.LOW))
    }

    @Test
    fun `conjunction sequence`() {
        val m = ConjunctionModule("a", listOf("b", "c"), listOf("d"))
        assertThat(m.response("b", Pulse.LOW), `is`(Pulse.HIGH))
        assertThat(m.response("c", Pulse.HIGH), `is`(Pulse.HIGH))
        assertThat(m.response("b", Pulse.HIGH), `is`(Pulse.LOW))
    }

}