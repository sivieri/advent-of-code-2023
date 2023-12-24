package me.sivieri.aoc2023.day21

import me.sivieri.aoc2023.common.Coordinate2D

enum class GardenCellValue {
    GARDEN_PLOT,
    ROCK;
}

data class GardenCell(
    val coordinates: Coordinate2D,
    val value: GardenCellValue
)
