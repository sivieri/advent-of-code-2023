package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.common.firstNotNullOrNull

class FoodProductionAlmanacProcessor(data: String) {

    private val almanac = FoodProductionAlmanac.parse(data)
    private val almanacV2 = FoodProductionAlmanacV2.parse(data)

    fun findLowestLocationNumber(): Long = almanac
        .seeds
        .minOfOrNull { seed ->
            val soil = findRange(seed, almanac.seedToSoil)
            val fertilizer = findRange(soil, almanac.soilToFertilizer)
            val water = findRange(fertilizer, almanac.fertilizerToWater)
            val light = findRange(water, almanac.waterToLight)
            val temperature = findRange(light, almanac.lightToTemperature)
            val humidity = findRange(temperature, almanac.temperatureToHumidity)
            findRange(humidity, almanac.humidityToLocation)
        }!!

    fun findLowestLocationNumberV2(): Long = almanacV2
        .seedRanges
        .minOfOrNull { seedRange ->
            println("Seed range $seedRange")
            (seedRange.start..seedRange.end).minOfOrNull { seed ->
                val soil = findRange(seed, almanac.seedToSoil)
                val fertilizer = findRange(soil, almanac.soilToFertilizer)
                val water = findRange(fertilizer, almanac.fertilizerToWater)
                val light = findRange(water, almanac.waterToLight)
                val temperature = findRange(light, almanac.lightToTemperature)
                val humidity = findRange(temperature, almanac.temperatureToHumidity)
                findRange(humidity, almanac.humidityToLocation)
            }!!
        }!!

    private fun findRange(value: Long, ranges: List<AlmanacRangesInclusive>): Long = ranges
        .firstNotNullOrNull { it.findDestination(value) } ?: value

}