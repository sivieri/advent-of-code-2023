package me.sivieri.aoc2023.day6

import me.sivieri.aoc2023.common.multiplyBy

class BoatRacer(data: String) {

    private val races: List<BoatRace>
    private val race: BoatRace

    init {
        val (time, distance) = data.split("\n")
        val times = time.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
        val distances = distance.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
        races = times.zip(distances).map { BoatRace(it.first, it.second) }
        race = BoatRace(
            time.split(":")[1].replace(" ", "").toLong(),
            distance.split(":")[1].replace(" ", "").toLong(),
        )
    }

    fun countWaysToWin(): Long = races.multiplyBy { it.countWaysToWin().toLong() }

    fun countSingleRace(): Long = race.countWaysToWin().toLong()

}