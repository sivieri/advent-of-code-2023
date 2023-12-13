package me.sivieri.aoc2023.day3

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(3)
        val engineSchematic = EngineSchematic(data)
        val result = engineSchematic.gearsRatio()
        println(result)
    }

}