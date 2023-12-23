package me.sivieri.aoc2023.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CircuitTest {

    @Test
    fun `part 1 example 1`() {
        val input = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """.trimIndent().split("\n")
        val circuit = Circuit(input)
        val result = circuit.countPulses(1000)
        assertThat(result.first.toLong() * result.second.toLong(), `is`(32000000))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
        """.trimIndent().split("\n")
        val circuit = Circuit(input)
        val result = circuit.countPulses(1000)
        assertThat(result.first.toLong() * result.second.toLong(), `is`(11687500))
    }

}