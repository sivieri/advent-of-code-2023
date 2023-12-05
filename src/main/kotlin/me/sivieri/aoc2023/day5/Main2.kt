package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(5)
        val foodProductionAlmanacProcessor = FoodProductionAlmanacProcessor(data)
        val result = foodProductionAlmanacProcessor.findLowestLocationNumberV2()
        println(result)
    }

}