package me.sivieri.aoc2023.day6

import me.sivieri.aoc2023.multiplyBy

class BoatRacer(data: String) {

    private val races: List<BoatRace>

    init {
        val (time, distance) = data.split("\n")
        val times = time.split(":")[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        val distances = distance.split(":")[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        races = times.zip(distances).map { BoatRace(it.first, it.second) }
    }

    fun countWaysToWin(): Long = races.multiplyBy { it.countWaysToWin().toLong() }

}