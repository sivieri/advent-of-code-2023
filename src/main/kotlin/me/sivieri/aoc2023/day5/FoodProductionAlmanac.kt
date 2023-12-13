package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.common.tail

data class FoodProductionAlmanac(
    val seeds: List<Long>,
    val seedToSoil: List<AlmanacRangesInclusive>,
    val soilToFertilizer: List<AlmanacRangesInclusive>,
    val fertilizerToWater: List<AlmanacRangesInclusive>,
    val waterToLight: List<AlmanacRangesInclusive>,
    val lightToTemperature: List<AlmanacRangesInclusive>,
    val temperatureToHumidity: List<AlmanacRangesInclusive>,
    val humidityToLocation: List<AlmanacRangesInclusive>
) {
    companion object {
        fun parse(data: String): FoodProductionAlmanac {
            val blocks = data.split("\n\n")
            return FoodProductionAlmanac(
                blocks[0].split(":")[1].trim().split(" ").map { it.toLong() },
                parseRanges(blocks[1]),
                parseRanges(blocks[2]),
                parseRanges(blocks[3]),
                parseRanges(blocks[4]),
                parseRanges(blocks[5]),
                parseRanges(blocks[6]),
                parseRanges(blocks[7])
            )
        }

        private fun parseRanges(s: String): List<AlmanacRangesInclusive> = s
            .split("\n")
            .tail()
            .filterNot { it.isBlank() }
            .map { AlmanacRangesInclusive.parse(it) }
    }
}
