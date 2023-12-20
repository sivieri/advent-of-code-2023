package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.day17.CityBlock
import org.jgrapht.Graph
import org.jgrapht.GraphPath

class ShortestPathWithConstraintGraphPath(
    private val graph: Graph<CityBlock, DirectionalEdge>,
    private val vertexAccumulator: VertexAccumulator
): GraphPath<CityBlock, DirectionalEdge> {
    override fun getGraph(): Graph<CityBlock, DirectionalEdge> = graph

    override fun getStartVertex(): CityBlock = vertexAccumulator.vertices.first()

    override fun getEndVertex(): CityBlock = vertexAccumulator.vertices.last()

    override fun getEdgeList(): MutableList<DirectionalEdge> = throw NotImplementedError("Unsupported")

    override fun getVertexList(): MutableList<CityBlock> = vertexAccumulator.vertices.toMutableList()

    override fun getWeight(): Double = vertexAccumulator.weight.toDouble()
}
