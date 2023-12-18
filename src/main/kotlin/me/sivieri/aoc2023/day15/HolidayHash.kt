package me.sivieri.aoc2023.day15

object HolidayHash {

    fun hash(input: String): Int = input.fold(0) { acc, c ->
        ((acc + c.code) * 17) % 256
    }

}