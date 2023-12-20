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
        val visited = mutableMapOf<CityBlock, Boolean>()
        val queue = PriorityQueue<VertexAccumulator>()
        queue.add(VertexAccumulator(source, 0, emptyList(), Direction.RIGHT))
        queue.add(VertexAccumulator(source, 0, emptyList(), Direction.DOWN))
        var found = false
        while (queue.size > 0 && !found) {
            println("Queue size: ${queue.size}")
            val top = queue.remove()
            if (top.vertex != source && visited.getOrDefault(top.vertex, false)) continue
            visited[top.vertex] = true
            val nextVertices = findNextVertices(top.vertex, top.enteringDirection)
            nextVertices.forEach { (other, direction, path) ->
                if (!visited.getOrDefault(other, false)) {
                    val vcw = VertexAccumulator(
                        other,
                        top.weight + path.sumOf { it.heatLoss },
                        top.vertices.plus(path),
                        direction
                    )
                    queue.add(vcw)
                    if (other == sink) found = true
                }
            }
        }
        if (!found) throw IllegalStateException("Unable to find a path between $source and $sink")
        return queue.toList().find { it.vertex == sink }!!
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
