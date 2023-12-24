package me.sivieri.aoc2023.day21

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(21)
        val garden = Garden(data)
        val covered = garden.coveredAreas(64)
        println(covered)
    }

}