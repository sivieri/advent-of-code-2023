package me.sivieri.aoc2023.day10

import me.sivieri.aoc2023.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(10)
        val pipeMaze = PipeMaze(data, 'L')
        val spaces = pipeMaze.countInternalSpaces()
        println(spaces)
    }

}