package me.sivieri.aoc2023.day20

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(20)
        val circuit = Circuit(data)
        val result = circuit.countPulses(1000).let { it.first.toLong() * it.second.toLong() }
        println(result)
    }

}