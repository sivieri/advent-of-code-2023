package me.sivieri.aoc2023.day2

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(2)
        val cubeGameSolver = CubeGameSolver(data)
        val result = cubeGameSolver.powerSum()
        println(result)
    }

}