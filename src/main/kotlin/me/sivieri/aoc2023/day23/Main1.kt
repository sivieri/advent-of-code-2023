package me.sivieri.aoc2023.day23

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(23)
        val trails = HikingTrails(data)
        val result = trails.findLongestPath()
        println(result)
    }

}