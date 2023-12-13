package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.common.tail

data class FoodProductionAlmanacV2(
    val seedRanges: List<SeedRangeInclusive>,
    val seedToSoil: List<AlmanacRangesInclusive>,
    val soilToFertilizer: List<AlmanacRangesInclusive>,
    val fertilizerToWater: List<AlmanacRangesInclusive>,
    val waterToLight: List<AlmanacRangesInclusive>,
    val lightToTemperature: List<AlmanacRangesInclusive>,
    val temperatureToHumidity: List<AlmanacRangesInclusive>,
    val humidityToLocation: List<AlmanacRangesInclusive>
) {
    companion object {
        fun parse(data: String): FoodProductionAlmanacV2 {
            val blocks = data.split("\n\n")
            val seeds = blocks[0]
                .split(":")[1]
                .trim()
                .split(" ")
                .map { it.toLong() }
                .chunked(2)
                .map { SeedRangeInclusive(it[0], it[0] + it[1] - 1, it[1]) }
            return FoodProductionAlmanacV2(
                seeds,
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
