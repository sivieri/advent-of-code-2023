package me.sivieri.aoc2023.day1

import me.sivieri.aoc2023.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(1)
        val calibrationDoc = CalibrationDoc()
        val result = calibrationDoc.getCalibrationTotal(data)
        println(result)
    }

}