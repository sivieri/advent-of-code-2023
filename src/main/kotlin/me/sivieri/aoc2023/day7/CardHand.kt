package me.sivieri.aoc2023.day7

import me.sivieri.aoc2023.common.replace

data class CardHand(
    val cards: List<Char>,
    val bid: Int
): Comparable<CardHand> {

    fun handType(): CardHandType {
        return if (JOKER in cards) {
            val c = CARD_ORDER.filterNot { it == JOKER }.reversed().maxBy { sub ->
                noJokerHandType(cards.replace(JOKER, sub)).rank
            }
            noJokerHandType(cards.replace(JOKER, c))
        }
        else {
            noJokerHandType(this.cards)
        }
    }

    private fun noJokerHandType(cards: List<Char>): CardHandType {
        val grouped = cards.groupingBy { it }.eachCount()
        val counts = grouped.values
        return when {
            counts.max() == 5 -> CardHandType.FIVE_OF_A_KIND
            counts.max() == 4 -> CardHandType.FOUR_OF_A_KIND
            counts.containsAll(listOf(3, 2)) -> CardHandType.FULL_HOUSE
            counts.max() == 3 -> CardHandType.THREE_OF_A_KIND
            counts.count { it == 2 } == 2 -> CardHandType.TWO_PAIR
            counts.max() == 2 -> CardHandType.ONE_PAIR
            counts.max() == 1 -> CardHandType.HIGH_CARD
            else -> throw IllegalArgumentException("Strange counts: $grouped")
        }
    }

    override fun compareTo(other: CardHand): Int {
        val typeComparison = this.handType().compareRank(other.handType())
        when {
            typeComparison > 0 -> return typeComparison
            typeComparison < 0 -> return typeComparison
            else -> {
                this.cards.zip(other.cards).forEach { (card1, card2) ->
                    val cardComparison = CARD_ORDER.indexOf(card1).compareTo(CARD_ORDER.indexOf(card2))
                    when {
                        cardComparison > 0 -> return cardComparison
                        cardComparison < 0 -> return cardComparison
                        else -> {}
                    }
                }
                return 0
            }
        }
    }

    companion object {
        private const val JOKER = 'J'
        private val CARD_ORDER = listOf(JOKER, '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')

        fun parse(line: String): CardHand {
            val (cards, bid) = line.split(" ")
            return CardHand(cards.toList(), bid.toInt())
        }
    }
}
