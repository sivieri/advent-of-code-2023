package me.sivieri.aoc2023.day7

import me.sivieri.aoc2023.common.zipWithIndex

class CamelCards {

    fun calculateTotalWinnings(hands: List<CardHand>): Long = hands
        .sortByRank()
        .entries
        .sumOf { it.value * it.key.bid.toLong() }

    companion object {
        fun List<CardHand>.sortByRank(): Map<CardHand, Int> {
            val sorted = this
                .sortedWith { o1, o2 -> o1.compareTo(o2) }
            return sorted.zipWithIndex { it }
                .associate { Pair(it.second, it.first + 1) }
        }
    }

}