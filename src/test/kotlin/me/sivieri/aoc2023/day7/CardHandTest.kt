package me.sivieri.aoc2023.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CardHandTest {

    @Test
    fun `parse a line`() {
        val line = "32T3K 765"
        val card = CardHand("32T3K".toList(), 765)
        assertThat(CardHand.parse(line), `is`(card))
    }

    @Test
    fun `five of a kind`() {
        val card = CardHand("AAAAA".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.FIVE_OF_A_KIND))
    }

    @Test
    fun `four of a kind`() {
        val card = CardHand("AA8AA".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.FOUR_OF_A_KIND))
    }

    @Test
    fun `full house`() {
        val card = CardHand("23332".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.FULL_HOUSE))
    }

    @Test
    fun `three of a kind`() {
        val card = CardHand("TTT98".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.THREE_OF_A_KIND))
    }

    @Test
    fun `two pair`() {
        val card = CardHand("23432".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.TWO_PAIR))
    }

    @Test
    fun `one pair`() {
        val card = CardHand("A23A4".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.ONE_PAIR))
    }

    @Test
    fun `high card`() {
        val card = CardHand("23456".toList(), 0)
        assertThat(card.handType(), `is`(CardHandType.HIGH_CARD))
    }

    @Test
    fun `greater type`() {
        val card1 = CardHand("AAAAA".toList(), 0)
        val card2 = CardHand("AA8AA".toList(), 0)
        assertTrue(card1 > card2)
    }

    @Test
    fun `smaller type`() {
        val card1 = CardHand("A23A4".toList(), 0)
        val card2 = CardHand("AA8AA".toList(), 0)
        assertTrue(card1 < card2)
    }

    @Test
    fun `same type, greater card`() {
        val card1 = CardHand("AAAAA".toList(), 0)
        val card2 = CardHand("JJJJJ".toList(), 0)
        assertTrue(card1 > card2)
    }

    @Test
    fun `same type, smaller card`() {
        val card1 = CardHand("K126K".toList(), 0)
        val card2 = CardHand("A23A4".toList(), 0)
        assertTrue(card1 < card2)
    }

    @Test
    fun `same type, same cards`() {
        val card1 = CardHand("AAAAA".toList(), 0)
        val card2 = CardHand("AAAAA".toList(), 0)
        assertThat(card1.compareTo(card2), `is`(0))
    }

    @Test
    fun `joker test 1`() {
        val card = CardHand("J345A".toList(), 3)
        assertThat(card.handType(), `is`(CardHandType.ONE_PAIR))
    }

    @Test
    fun `joker test 2`() {
        val card = CardHand("JJJJJ".toList(), 29)
        assertThat(card.handType(), `is`(CardHandType.FIVE_OF_A_KIND))
    }

    @Test
    fun `joker test 3`() {
        val card = CardHand("JJJJ2".toList(), 31)
        assertThat(card.handType(), `is`(CardHandType.FIVE_OF_A_KIND))
    }

    @Test
    fun `joker test 4`() {
        val card = CardHand("2JJJJ".toList(), 41)
        assertThat(card.handType(), `is`(CardHandType.FIVE_OF_A_KIND))
    }

    @Test
    fun `jokers order`() {
        val bids = listOf(
            CardHand("JJJJJ".toList(), 29),
            CardHand("2JJJJ".toList(), 41),
            CardHand("JJJJ2".toList(), 31),
        ).sortedWith { o1, o2 -> o1.compareTo(o2) }.map { it.bid }
        assertThat(bids, `is`(listOf(29, 31, 41)))
    }

}