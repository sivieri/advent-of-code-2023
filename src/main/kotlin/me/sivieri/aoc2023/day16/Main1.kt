package me.sivieri.aoc2023.day16

import me.sivieri.aoc2023.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(16)
        val contraption = BeamContraption(data)
        val result = contraption.countEnergizedCells()
        println(result)
    }

}