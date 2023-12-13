package me.sivieri.aoc2023.day4

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(4).map { Scratchcard.parse(it) }
        val winningComputer = WinningComputer()
        val result = winningComputer.countScratchcards(data)
        println(result)
    }

}