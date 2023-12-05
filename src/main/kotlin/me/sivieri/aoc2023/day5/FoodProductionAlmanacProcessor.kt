package me.sivieri.aoc2023.day5

import me.sivieri.aoc2023.firstNotNullOrNull

class FoodProductionAlmanacProcessor(data: String) {

    private val almanac = FoodProductionAlmanac.parse(data)

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

    private fun findRange(value: Long, ranges: List<AlmanacRangesInclusive>): Long = ranges
        .firstNotNullOrNull { it.findDestination(value) } ?: value

}