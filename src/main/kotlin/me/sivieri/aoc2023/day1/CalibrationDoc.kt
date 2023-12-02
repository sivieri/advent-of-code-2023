package me.sivieri.aoc2023.day1

class CalibrationDoc {

    fun getCalibrationTotal(data: List<String>): Int = data.sumOf {
        "${it.first { it.isDigit() }}${it.last { it.isDigit() }}".toInt()
    }

    fun getCalibrationTotalWithLetters(data: List<String>): Int = data.sumOf {
        val min = it
            .firstDigitIndexes()
            .filter { it.second != -1 }
            .minBy { it.second }
            .first
        val max = it
            .lastDigitIndexes()
            .filter { it.second != -1 }
            .maxBy { it.second }
            .first
        "$min$max".toInt()
    }

    companion object {
        private fun String.firstDigitIndexes(): List<Pair<Int, Int>> = listOf(
            Pair(0, this.indexOf('0')),
            Pair(1, this.indexOf('1')),
            Pair(2, this.indexOf('2')),
            Pair(3, this.indexOf('3')),
            Pair(4, this.indexOf('4')),
            Pair(5, this.indexOf('5')),
            Pair(6, this.indexOf('6')),
            Pair(7, this.indexOf('7')),
            Pair(8, this.indexOf('8')),
            Pair(9, this.indexOf('9')),
            Pair(1, this.indexOf("one")),
            Pair(2, this.indexOf("two")),
            Pair(3, this.indexOf("three")),
            Pair(4, this.indexOf("four")),
            Pair(5, this.indexOf("five")),
            Pair(6, this.indexOf("six")),
            Pair(7, this.indexOf("seven")),
            Pair(8, this.indexOf("eight")),
            Pair(9, this.indexOf("nine"))
        )
        private fun String.lastDigitIndexes(): List<Pair<Int, Int>> = listOf(
            Pair(0, this.lastIndexOf('0')),
            Pair(1, this.lastIndexOf('1')),
            Pair(2, this.lastIndexOf('2')),
            Pair(3, this.lastIndexOf('3')),
            Pair(4, this.lastIndexOf('4')),
            Pair(5, this.lastIndexOf('5')),
            Pair(6, this.lastIndexOf('6')),
            Pair(7, this.lastIndexOf('7')),
            Pair(8, this.lastIndexOf('8')),
            Pair(9, this.lastIndexOf('9')),
            Pair(1, this.lastIndexOf("one")),
            Pair(2, this.lastIndexOf("two")),
            Pair(3, this.lastIndexOf("three")),
            Pair(4, this.lastIndexOf("four")),
            Pair(5, this.lastIndexOf("five")),
            Pair(6, this.lastIndexOf("six")),
            Pair(7, this.lastIndexOf("seven")),
            Pair(8, this.lastIndexOf("eight")),
            Pair(9, this.lastIndexOf("nine"))
        )
    }

}