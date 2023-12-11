package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(11)
        val observatory = Observatory(data, weight = 2)
        val result = observatory.sumShortestPaths()
        println(result)
    }

}