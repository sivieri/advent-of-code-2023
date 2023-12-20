package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.common.getOtherVertex
import me.sivieri.aoc2023.day17.CityBlock
import org.jgrapht.GraphPath
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.graph.SimpleDirectedGraph
import java.util.*

class ShortestPathWithConstraint(
    private val graph: SimpleDirectedGraph<CityBlock, DirectionalEdge>
): ShortestPathAlgorithm<CityBlock, DirectionalEdge> {
    override fun getPaths(source: CityBlock): ShortestPathAlgorithm.SingleSourcePaths<CityBlock, DirectionalEdge> = throw NotImplementedError("Unavailable")

    override fun getPath(source: CityBlock, sink: CityBlock): GraphPath<CityBlock, DirectionalEdge> =
        ShortestPathWithConstraintGraphPath(graph, findThePath(source, sink))

    override fun getPathWeight(source: CityBlock, sink: CityBlock): Double = findThePath(source, sink).weight.toDouble()

    private fun findThePath(source: CityBlock, sink: CityBlock): VertexAccumulator {
        val visited = mutableMapOf<VisitedCityBlock, Int>()
        visited[VisitedCityBlock(source, Direction.RIGHT)] = 0
        visited[VisitedCityBlock(source, Direction.DOWN)] = 0
        val queue = PriorityQueue<VertexAccumulator>()
        queue.add(VertexAccumulator(source, 0, emptyList(), Direction.RIGHT))
        queue.add(VertexAccumulator(source, 0, emptyList(), Direction.DOWN))
        while (queue.size > 0) {
            println("Queue size: ${queue.size}")
            if (queue.peek().vertex == sink) break
            val top = queue.remove()
            val nextVertices = findNextVertices(top.vertex, top.enteringDirection)
            nextVertices.forEach { (other, direction, path) ->
                val newCost = top.weight + path.sumOf { it.heatLoss }
                val visitedCityBlock = VisitedCityBlock(other, direction)
                if (visitedCityBlock !in visited.keys || newCost < visited[visitedCityBlock]!!) {
                    visited[visitedCityBlock] = newCost
                    val vcw = VertexAccumulator(
                        other,
                        top.weight + path.sumOf { it.heatLoss },
                        top.vertices.plus(path),
                        direction
                    )
                    queue.add(vcw)
                }
            }
        }
        return queue.remove()
    }

    private fun findNextVertices(vertex: CityBlock, enteringDirection: Direction): List<NextVertex> =
        enteringDirection
            .orthogonal()
            .flatMap { d ->
                val first = graph
                    .outgoingEdgesOf(vertex)
                    .filter { it.direction == d }
                    .map { graph.getEdgeTarget(it) }
                    .firstOrNull()
                    ?.let { NextVertex(it, d, listOf(it)) }
                val second = first
                    ?.let { graph.outgoingEdgesOf(first.vertex).filter { it.direction == d }.map { graph.getEdgeTarget(it) }.firstOrNull() }
                    ?.let { NextVertex(it, d, first.path.plus(it)) }
                val third = second
                ?.let { graph.outgoingEdgesOf(second.vertex).filter { it.direction == d }.map { graph.getEdgeTarget(it) }.firstOrNull() }
                ?.let { NextVertex(it, d, second.path.plus(it)) }
                listOf(first, second, third)
            }
            .filterNotNull()

}
