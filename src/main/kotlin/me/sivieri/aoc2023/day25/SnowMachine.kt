package me.sivieri.aoc2023.day25

import me.sivieri.aoc2023.common.sort
import me.sivieri.aoc2023.common.threeCombinations
import org.jgrapht.alg.connectivity.BiconnectivityInspector
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

class SnowMachine(data: List<String>) {

    private val pairs = data
        .flatMap {
            val (source, dest) = it.split(": ")
            dest.split(" ").map { source to it }
        }
        .map { it.sort() }
        .toSet()
        .toList()
    private val nodes = pairs
        .flatMap { listOf(it.first, it.second) }
        .toSet()

    fun splitMachine(): Long {
        var counter = 0
        (0 until pairs.size).forEach { i ->
            (i + 1 until pairs.size).forEach { j ->
                (j + 1 until pairs.size).forEach { k ->
                    if (++counter % 100 == 0) println(counter)
                    val graph = SimpleGraph<String, DefaultEdge>(DefaultEdge::class.java)
                    val excluded = listOf(pairs[i], pairs[j], pairs[k])
                    nodes.forEach { graph.addVertex(it) }
                    pairs.forEach {
                        if (it !in excluded) graph.addEdge(it.first, it.second)
                    }
                    val inspector = BiconnectivityInspector(graph)
                    val parts = inspector.connectedComponents.map { it.vertexSet().size }
                    if (parts.size == 2) return parts[0].toLong() * parts[1].toLong()
                }
            }
        }
        throw IllegalStateException("Unable to find a triplet")
    }

}