package me.sivieri.aoc2023.day12

import me.sivieri.aoc2023.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(12)
        val springs = Springs(data)
        val result = springs.findAllArrangements(extended = true)
        println(result)
    }

}