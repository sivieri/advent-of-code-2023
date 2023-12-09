package me.sivieri.aoc2023.day9

class OASIS(valueHistory: List<String>) {

    private val histories = valueHistory.map { ValueHistory(it.split(" ").map { it.toInt() }) }

    fun sumAllPredictions(): Int = histories.sumOf { it.findNextValue() }

}