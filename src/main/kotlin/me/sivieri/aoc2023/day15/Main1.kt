package me.sivieri.aoc2023.day15

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(15)
        val manual = InitializationManual(data)
        val result = manual.sumInstructions()
        println(result)
    }

}