package me.sivieri.aoc2023.day13

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val patternInterpreter = PatternInterpreter(data)
        val result = patternInterpreter.findReflectionPatternsWithChanges()
        println(result)
    }

}