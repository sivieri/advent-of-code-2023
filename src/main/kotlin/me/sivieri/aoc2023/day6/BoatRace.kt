package me.sivieri.aoc2023.day6

data class BoatRace(
    val duration: Int,
    val maxDistance: Int
) {

    fun countWaysToWin(): Int = (1 until duration)
        .count { l ->
            val remaining = duration - l
            remaining * l > maxDistance
        }

}
