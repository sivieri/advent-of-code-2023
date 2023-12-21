package me.sivieri.aoc2023.day18

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(18)
        val plan = DigPlan(data)
        val result = plan.countDigSpace()
        println(result)
    }

}