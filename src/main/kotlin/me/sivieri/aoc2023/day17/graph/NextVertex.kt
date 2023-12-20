package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.day17.CityBlock

data class NextVertex(
    val vertex: CityBlock,
    val direction: Direction,
    val path: List<CityBlock>
)
