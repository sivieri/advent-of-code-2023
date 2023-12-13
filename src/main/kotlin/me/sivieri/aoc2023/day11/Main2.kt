package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(11)
        val observatory = Observatory(data, weight = 1_000_000)
        val result = observatory.sumShortestPaths()
        println(result)
    }

}