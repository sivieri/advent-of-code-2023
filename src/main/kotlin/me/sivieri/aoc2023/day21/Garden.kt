package me.sivieri.aoc2023.day21

import me.sivieri.aoc2023.common.Coordinate2D
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

class Garden(input: String) {

    private val matrix = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size
    private val graph = SimpleDirectedWeightedGraph<GardenCell, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    private var start: Coordinate2D = Coordinate2D(-1, -1)
    private val nodes: Map<Coordinate2D, GardenCell>

    init {
        nodes = (0 until maxX).flatMap { x ->
            (0 until maxY).map { y ->
                val value = when (matrix[y][x]) {
                    '.' -> GardenCellValue.GARDEN_PLOT
                    '#' -> GardenCellValue.ROCK
                    'S' -> {
                        start = Coordinate2D(x, y)
                        GardenCellValue.GARDEN_PLOT
                    }
                    else -> throw IllegalArgumentException("Unknown character ${matrix[y][x]}")
                }
                val c = Coordinate2D(x, y)
                c to GardenCell(c, value)
            }
        }.toMap()
        nodes.forEach { graph.addVertex(it.value) }
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                val c = Coordinate2D(x, y)
                val neighbors = setOf(
                    Coordinate2D((x - 1).coerceAtLeast(0), y),
                    Coordinate2D((x + 1).coerceAtMost(maxX - 1), y),
                    Coordinate2D(x, (y - 1).coerceAtLeast(0)),
                    Coordinate2D(x, (y + 1).coerceAtMost(maxY - 1))
                ).minus(c)
                neighbors.forEach { n ->
                    val source = nodes[c]!!
                    val dest = nodes[n]!!
                    val weight = if (source.value == GardenCellValue.ROCK || dest.value == GardenCellValue.ROCK) Double.POSITIVE_INFINITY
                    else 1.0
                    graph.addEdge(source, dest)
                    graph.setEdgeWeight(source, dest, weight)
                }
            }
        }
    }

    fun coveredAreas(steps: Int): Int {
        val start = nodes[start]!!
        val sp = DijkstraShortestPath(graph)
        val cells = nodes
            .values
            .filter { it.value == GardenCellValue.GARDEN_PLOT && (steps % 2 == 0 || it != start) }
            .filter {
                val l = sp.getPath(start, it)?.length ?: Int.MAX_VALUE
                l <= steps && (steps % 2 == l % 2)
            }
        println(matrixString(cells.map { it.coordinates }))
        return cells.size
    }

    private fun matrixString(cells: List<Coordinate2D>): String = (0 until maxY)
        .joinToString("\n") { y ->
            (0 until maxX).joinToString("") { x ->
                if (Coordinate2D(x, y) in cells) "O"
                else matrix[y][x].toString()
            }
        }

}