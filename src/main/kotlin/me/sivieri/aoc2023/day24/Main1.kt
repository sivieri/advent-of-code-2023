package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(24)
        val hail = Hail(data)
        val result = hail.countIntersectingHailstones2D(200000000000000, 400000000000000)
        println(result)
    }

}