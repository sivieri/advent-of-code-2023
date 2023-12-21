package me.sivieri.aoc2023.day19

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(19)
        val sorting = PartsSorting(data)
        val result = sorting.findTotalCombinations()
        println(result)
    }

}