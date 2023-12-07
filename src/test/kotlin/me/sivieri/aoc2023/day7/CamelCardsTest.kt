package me.sivieri.aoc2023.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class CamelCardsTest {

    @Test
    @Ignore // the code is now considering Jokers, and adding ifs would be ugly
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

    @Test
    fun `part 2 example`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().split("\n").map { CardHand.parse(it) }
        val camelCards = CamelCards()
        val result = camelCards.calculateTotalWinnings(input)
        assertThat(result, `is`(5905))
    }

    @Test
    fun `another part 2 example`() {
        val input = """
            2345A 2
            2345J 5
            J345A 3
            32T3K 7
            T55J5 17
            KK677 11
            KTJJT 23
            QQQJA 19
            JJJJJ 29
            JAAAA 37
            AAAAJ 43
            AAAAA 53
            2AAAA 13
            2JJJJ 41
            JJJJ2 31
        """.trimIndent().split("\n").map { CardHand.parse(it) }
        val camelCards = CamelCards()
        val result = camelCards.calculateTotalWinnings(input)
        assertThat(result, `is`(3667))
    }

}