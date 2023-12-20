package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.day17.CityBlock

data class VertexAccumulator(
    val vertex: CityBlock,
    val weight: Int,
    val vertices: List<CityBlock>,
    val enteringDirection: Direction
): Comparable<VertexAccumulator> {
    override fun compareTo(other: VertexAccumulator): Int = this.weight - other.weight
}
