package me.sivieri.aoc2023.day4

class WinningComputer {

    fun calculateTotalPoints(cards: List<Scratchcard>): Int = cards.sumOf { it.points() }

}