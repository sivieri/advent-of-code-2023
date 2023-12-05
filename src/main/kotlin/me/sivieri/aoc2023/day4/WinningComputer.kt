package me.sivieri.aoc2023.day4

class WinningComputer {

    fun calculateTotalPoints(cards: List<Scratchcard>): Int = cards.sumOf { it.points() }

    fun countScratchcards(cards: List<Scratchcard>): Int {
        cards.forEachIndexed { index, card ->
            val score = card.score()
            if (score > 0) {
                cards
                    .subList(
                        (index + 1).coerceAtMost(cards.size),
                        (index + 1 + score).coerceAtMost(cards.size)
                    )
                    .forEach { it.instances += card.instances }
            }
        }
        return cards.sumOf { it.instances }
    }

}