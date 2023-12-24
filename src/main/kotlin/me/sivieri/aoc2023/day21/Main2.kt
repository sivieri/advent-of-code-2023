package me.sivieri.aoc2023.day21

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(21)
        val garden = Garden(data)
        val covered = garden.coveredAreasWithExtension(26501365)
        println(covered)
    }

}