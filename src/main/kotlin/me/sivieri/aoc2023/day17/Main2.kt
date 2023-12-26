package me.sivieri.aoc2023.day17

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(17)
        val map = CityMap(data)
        val loss = map.calculateUltraMinHeatLoss()
        println(loss)
    }

}