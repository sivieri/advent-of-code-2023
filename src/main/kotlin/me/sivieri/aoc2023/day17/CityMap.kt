package me.sivieri.aoc2023.day17

import me.sivieri.aoc2023.common.Coordinate2D
import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.day17.graph.DirectionalEdge
import me.sivieri.aoc2023.day17.graph.NextVertex
import me.sivieri.aoc2023.day17.graph.ShortestPathWithConstraint
import org.jgrapht.graph.SimpleDirectedGraph

class CityMap(input: String) {

    private val city = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.map { it.toString().toInt() }.toTypedArray() }
        .toTypedArray()
    private val maxX = city[0].size
    private val maxY = city.size
    private val start = Coordinate2D(0, 0)
    private val end = Coordinate2D(maxX - 1, maxY - 1)
    private val graph = SimpleDirectedGraph<CityBlock, DirectionalEdge>(DirectionalEdge::class.java)
    private val blocks: Map<Coordinate2D, CityBlock>

    init {
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val block = CityBlock(Coordinate2D(x, y), city[y][x])
                graph.addVertex(block)
            }
        }
        blocks = graph.vertexSet().associateBy { it.coordinate }
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val cur = blocks[Coordinate2D(x, y)]!!
                if (x - 1 >= 0) {
                    val left = blocks[Coordinate2D(x - 1, y)]!!
                    graph.addEdge(cur, left, DirectionalEdge(Direction.LEFT))
                }
                if (x + 1 < maxX) {
                    val right = blocks[Coordinate2D(x + 1, y)]!!
                    graph.addEdge(cur, right, DirectionalEdge(Direction.RIGHT))
                }
                if (y - 1 >= 0) {
                    val up = blocks[Coordinate2D(x, y - 1)]!!
                    graph.addEdge(cur, up, DirectionalEdge(Direction.UP))
                }
                if (y + 1 < maxY) {
                    val down = blocks[Coordinate2D(x, y + 1)]!!
                    graph.addEdge(cur, down, DirectionalEdge(Direction.DOWN))
                }
            }
        }
    }

    fun calculateMinHeatLoss(): Int {
        val spc = ShortestPathWithConstraint(graph) { vertex, enteringDirection ->
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
                        ?.let {
                            graph.outgoingEdgesOf(first.vertex).filter { it.direction == d }
                                .map { graph.getEdgeTarget(it) }.firstOrNull()
                        }
                        ?.let { NextVertex(it, d, first.path.plus(it)) }
                    val third = second
                        ?.let {
                            graph.outgoingEdgesOf(second.vertex).filter { it.direction == d }
                                .map { graph.getEdgeTarget(it) }.firstOrNull()
                        }
                        ?.let { NextVertex(it, d, second.path.plus(it)) }
                    listOf(first, second, third)
                }
                .filterNotNull()
        }
        val path = spc.getPath(blocks[start]!!, blocks[end]!!)
        println(cityToString(path.vertexList.map { it.coordinate }))
        return path.weight.toInt()
    }

    fun calculateUltraMinHeatLoss(): Int {
        val spc = ShortestPathWithConstraint(graph) { vertex, enteringDirection ->
            enteringDirection
                .orthogonal()
                .flatMap { d ->
                    var start: CityBlock? = vertex
                    val steps = (1..10)
                        .map { i ->
                            val dest = start
                                ?.let { graph.outgoingEdgesOf(start)
                                    .filter { it.direction == d }
                                    .map { graph.getEdgeTarget(it) }
                                    .firstOrNull()
                                }
                            start = dest
                            i to dest
                        }
                    (4..10)
                        .mapNotNull { i ->
                            val steps = steps.filter { it.first <= i }.mapNotNull { it.second }
                            if (steps.size != i) null
                            else NextVertex(steps.first(), d, steps)
                        }
                }
        }
        val path = spc.getPath(blocks[start]!!, blocks[end]!!)
        println(cityToString(path.vertexList.map { it.coordinate }))
        return path.weight.toInt()
    }

    private fun cityToString(coordinates: List<Coordinate2D>): String =
        (0 until maxY)
            .joinToString("\n") { y ->
                (0 until maxX)
                    .joinToString("") { x ->
                        if (Coordinate2D(x, y) in coordinates) "*"
                        else city[y][x].toString()
                    }
            }

}