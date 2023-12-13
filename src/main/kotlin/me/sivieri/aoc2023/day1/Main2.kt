package me.sivieri.aoc2023.day1

import me.sivieri.aoc2023.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(1)
        val calibrationDoc = CalibrationDoc()
        val result = calibrationDoc.getCalibrationTotalWithLetters(data)
        println(result)
    }

}