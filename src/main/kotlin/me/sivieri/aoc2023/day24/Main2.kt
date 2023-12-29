package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(24)
        val hail = Hail(data)
        val result = hail.findRockStartingPoint()
        println(result)
    }

}