package me.sivieri.aoc2023.day17.graph

data class VertexAccumulator(
    val vertex: VisitedCityBlock,
    val weight: Int,
    val cost: PathAccumulator,
    val vertices: List<VisitedCityBlock>
): Comparable<VertexAccumulator> {
    override fun compareTo(other: VertexAccumulator): Int = this.weight - other.weight
}
