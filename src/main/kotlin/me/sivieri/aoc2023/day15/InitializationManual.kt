package me.sivieri.aoc2023.day15

class InitializationManual(input: String) {

    private val manual = input.trim().split(",")

    fun sumInstructions(): Long = manual.fold(0L) { acc, it -> acc + HolidayHash.hash(it) }

}