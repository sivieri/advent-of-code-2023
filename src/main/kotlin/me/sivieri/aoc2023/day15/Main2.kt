package me.sivieri.aoc2023.day15

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(15)
        val manual = InitializationManual(data)
        val result = manual.focusingPower()
        println(result)
    }

}