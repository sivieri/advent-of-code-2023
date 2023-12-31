package me.sivieri.aoc2023.day7

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        // this won't work: the code is now considering Jokers, and adding ifs would be ugly
        val data = Utils.readInput(7).split("\n").filter { it.isNotBlank() }.map { CardHand.parse(it) }
        val camelCards = CamelCards()
        val result = camelCards.calculateTotalWinnings(data)
        println(result)
    }

}