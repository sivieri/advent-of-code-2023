package me.sivieri.aoc2023.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CamelCardsTest {

    @Test
    fun `part 1 example`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().split("\n").map { CardHand.parse(it) }
        val camelCards = CamelCards()
        val result = camelCards.calculateTotalWinnings(input)
        assertThat(result, `is`(6440))
    }

}