package me.sivieri.aoc2023.day22

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(22)
        val stack = BrickStack(data)
        val result = stack.countDisposableBricks()
        println(result)
    }

}