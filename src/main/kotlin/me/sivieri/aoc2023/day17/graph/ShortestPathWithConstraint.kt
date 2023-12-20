package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.common.getOtherVertex
import me.sivieri.aoc2023.day17.CityBlock
import org.jgrapht.GraphPath
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.graph.SimpleDirectedGraph
import java.util.*

class ShortestPathWithConstraint(
    private val graph: SimpleDirectedGraph<CityBlock, DirectionalEdge>,
    private val constraintCalculator: (edge: DirectionalEdge, cost: PathAccumulator) -> PathAccumulator,
    private val constraintCheck: (PathAccumulator) -> Boolean,
    private val initialCost: PathAccumulator
): ShortestPathAlgorithm<CityBlock, DirectionalEdge> {
    override fun getPaths(source: CityBlock): ShortestPathAlgorithm.SingleSourcePaths<CityBlock, DirectionalEdge> = throw NotImplementedError("Unavailable")

    override fun getPath(source: CityBlock, sink: CityBlock): GraphPath<CityBlock, DirectionalEdge> =
        ShortestPathWithConstraintGraphPath(graph, findThePath(source, sink))

    override fun getPathWeight(source: CityBlock, sink: CityBlock): Double = findThePath(source, sink).weight.toDouble()

    private fun findThePath(source: CityBlock, sink: CityBlock): VertexAccumulator {
        val visited = mutableMapOf<VisitedCityBlock, Boolean>()
        val queue = PriorityQueue<VertexAccumulator>()
        queue.add(VertexAccumulator(VisitedCityBlock(source, Direction.RIGHT), 0, initialCost, emptyList()))
        var found = false
        while (queue.size > 0 && !found) {
            println("Queue size: ${queue.size}")
            val top = queue.remove()
            if (top.vertex.block != source && visited.getOrDefault(top.vertex, false)) continue
            visited[top.vertex] = true
            graph.outgoingEdgesOf(top.vertex.block).forEach { edge ->
                val other = graph.getOtherVertex(top.vertex.block, edge)
                val otherVisited = VisitedCityBlock(other, edge.direction)
                val newCost = constraintCalculator(edge, top.cost)
                if (!visited.getOrDefault(otherVisited, false) && constraintCheck(newCost)) {
                    val vcw = VertexAccumulator(
                        otherVisited,
                        top.weight + other.heatLoss,
                        newCost,
                        top.vertices.plus(otherVisited)
                    )
                    queue.add(vcw)
                    if (other == sink) found = true
                }
            }
        }
        if (!found) throw IllegalStateException("Unable to find a path between $source and $sink")
        return queue.toList().find { it.vertex.block == sink }!!
    }

}
