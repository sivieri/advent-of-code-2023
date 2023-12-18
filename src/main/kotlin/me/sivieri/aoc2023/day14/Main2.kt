package me.sivieri.aoc2023.day14

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(14)
        val dish = Dish(data)
        val result = dish.calculateLoad(1_000_000_000)
        println(result)
    }

}