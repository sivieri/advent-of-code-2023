package me.sivieri.aoc2023.day13

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val patternInterpreter = PatternInterpreter(data)
        val result = patternInterpreter.findReflectionPatterns()
        println(result)
    }

}