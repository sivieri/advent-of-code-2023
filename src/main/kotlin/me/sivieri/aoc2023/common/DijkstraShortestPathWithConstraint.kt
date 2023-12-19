package me.sivieri.aoc2023.common

import org.jgrapht.GraphPath
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.graph.SimpleGraph
import java.util.*

interface VertexWithWeight {
    val weight: Int
}

private data class VertexCumulativeWeight<T, C>(
    val vertex: T,
    val weight: Int,
    val cost: C
): Comparable<VertexCumulativeWeight<T, C>> {
    override fun compareTo(other: VertexCumulativeWeight<T, C>): Int = this.weight - other.weight
}

class DijkstraShortestPathWithConstraint<V: VertexWithWeight, E, C>(
    private val graph: SimpleGraph<V, E>,
    private val constraintCalculator: (source: V, sink: V, edge: E, cost: C) -> C,
    private val constraintCheck: (C) -> Boolean,
    private val initialCost: C
): ShortestPathAlgorithm<V, E> {
    override fun getPath(source: V, sink: V): GraphPath<V, E> = throw NotImplementedError("Unavailable")
    override fun getPaths(source: V): ShortestPathAlgorithm.SingleSourcePaths<V, E> = throw NotImplementedError("Unavailable")

    override fun getPathWeight(source: V, sink: V): Double {
        val visited = mutableMapOf<V, Boolean>()
        val queue = PriorityQueue<VertexCumulativeWeight<V, C>>()
        queue.add(VertexCumulativeWeight(source, 0, initialCost))
        var found = false
        while (queue.size > 0 && !found) {
            val top = queue.remove()
            if (visited.getOrDefault(top.vertex, false)) continue
            visited[top.vertex] = true
            graph.edgesOf(top.vertex).forEach { edge ->
                val other = graph.getOtherVertex(top.vertex, edge)
                val newCost = constraintCalculator(top.vertex, other, edge, top.cost)
                if (!visited.getOrDefault(other, false) && constraintCheck(newCost)) {
                    val vcw = VertexCumulativeWeight(other, top.weight + other.weight, newCost)
                    queue.add(vcw)
                    if (other == sink) found = true
                }
            }
        }
        return queue.toList().find { it.vertex == sink }!!.weight.toDouble()
    }
}
