package me.sivieri.aoc2023.day6

data class BoatRace(
    val duration: Long,
    val maxDistance: Long
) {

    fun countWaysToWin(): Int = (1 until duration)
        .count { l ->
            val remaining = duration - l
            remaining * l > maxDistance
        }

}
