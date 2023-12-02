package me.sivieri.aoc2023.day1

class CalibrationDoc {

    fun getCalibrationTotal(data: List<String>): Int = data.sumOf {
        "${it.first { it.isDigit() }}${it.last { it.isDigit() }}".toInt()
    }

}