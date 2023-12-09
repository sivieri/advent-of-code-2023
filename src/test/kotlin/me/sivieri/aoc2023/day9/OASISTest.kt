package me.sivieri.aoc2023.day9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class OASISTest {

    @Test
    fun `part 1 example`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().split("\n")
        val oasis = OASIS(input)
        val result = oasis.sumAllForwardPredictions()
        assertThat(result, `is`(114))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().split("\n")
        val oasis = OASIS(input)
        val result = oasis.sumAllBackwardPredictions()
        assertThat(result, `is`(2))
    }

}