package me.sivieri.aoc2023.day20

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(20)
        val circuit = Circuit(data)
        val result = circuit.countButtonsToReceiver()
        println(result)
    }

}