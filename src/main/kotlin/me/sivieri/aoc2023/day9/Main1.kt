package me.sivieri.aoc2023.day9

import me.sivieri.aoc2023.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(9)
        val oasis = OASIS(data)
        val result = oasis.sumAllPredictions()
        println(result)
    }

}