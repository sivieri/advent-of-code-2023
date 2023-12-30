package me.sivieri.aoc2023.day25

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(25)
        val machine = SnowMachine(data)
        val result = machine.splitMachine()
        println(result)
    }

}