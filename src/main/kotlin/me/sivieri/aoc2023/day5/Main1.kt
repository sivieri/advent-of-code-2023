package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(5)
        val foodProductionAlmanacProcessor = FoodProductionAlmanacProcessor(data)
        val result = foodProductionAlmanacProcessor.findLowestLocationNumber()
        println(result)
    }

}