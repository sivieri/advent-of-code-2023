package me.sivieri.aoc2023.day7

enum class CardHandType(val rank: Int) {

    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    fun compareRank(other: CardHandType) = this.rank.compareTo(other.rank)

}