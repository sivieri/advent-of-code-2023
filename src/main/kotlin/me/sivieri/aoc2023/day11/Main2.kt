package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(11)
        val observatory = Observatory(data)
        val result = observatory.sumShortestPaths()
        println(result)
    }

}