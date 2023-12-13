package me.sivieri.aoc2023.day2

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(2)
        val cubeGameSolver = CubeGameSolver(data)
        val game = mapOf(Cube.RED to 12, Cube.GREEN to 13, Cube.BLUE to 14)
        val result = cubeGameSolver.validGames(game)
        println(result)
    }

}