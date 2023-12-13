package me.sivieri.aoc2023.day8

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(8)
        val desertNavigator = DesertNavigator(data)
        val result = desertNavigator.countStepsInParallel()
        println(result)
    }

}