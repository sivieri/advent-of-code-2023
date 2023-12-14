package me.sivieri.aoc2023.day14

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(14)
        val dish = Dish(data)
        val result = dish.calculateLoadTiltedNorth()
        println(result)
    }

}