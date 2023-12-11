package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.common.Coordinate2D
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultUndirectedWeightedGraph
import org.jgrapht.graph.DefaultWeightedEdge

data class Space(
    val space: Array<Array<Char>>,
    val maxX: Int,
    val maxY: Int,
    val expandedRows: List<Int>,
    val expandedColumns: List<Int>,
    val weight: Int
) {
    val galaxies: List<Galaxy>
    private val graph: Graph<Coordinate2D, DefaultWeightedEdge>
    private val pathSearcher: DijkstraShortestPath<Coordinate2D, DefaultWeightedEdge>

    init {
        var counter = 1
        galaxies = (0 until maxY)
            .flatMap { y -> (0 until maxX).map { x -> if (space[y][x] == GALAXY) Galaxy(counter++, Coordinate2D(x, y)) else null } }
            .filterNotNull()
        graph = DefaultUndirectedWeightedGraph(DefaultWeightedEdge::class.java)
        (0 until maxY).forEach { y -> (0 until maxX).forEach { x -> graph.addVertex(Coordinate2D(x, y)) } }
        (0 until maxY).forEach { y -> (0 until maxX).forEach { x ->
            val c = Coordinate2D(x, y)
            listOf(
                Coordinate2D((c.x - 1).coerceAtLeast(0), c.y),
                Coordinate2D((c.x + 1).coerceAtMost(maxX - 1), c.y),
                Coordinate2D(c.x, (c.y - 1).coerceAtLeast(0)),
                Coordinate2D(c.x, (c.y + 1).coerceAtMost(maxY - 1)),
            ).forEach {
                val weight = if (c.y in expandedRows || c.x in expandedColumns) weight.toDouble()
                else 1.0
                graph.addEdge(c, it)
                graph.setEdgeWeight(c, it, weight)
            }
        } }
        pathSearcher = DijkstraShortestPath(graph)
    }

    fun shortestPathLength(start: Galaxy, end: Galaxy): Long = pathSearcher.getPath(start.position, end.position).weight.toLong()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Space

        return galaxies == other.galaxies
    }

    override fun hashCode(): Int {
        return galaxies.hashCode()
    }

    companion object {
        private const val EMPTY = '.'
        private const val GALAXY = '#'

        fun fromString(data: String, weight: Int): Space {
            val lines = data
                .split("\n")
                .filter { it.isNotBlank() }
            val maxX = lines[0].length
            val maxY = lines.size
            val initial = lines.map { it.toList() }
            val columnIndexes = (0 until maxX).mapNotNull { x ->
                if (initial.map { it[x] }.all { it == EMPTY }) x
                else null
            }
            val rowIndexes = (0 until maxY).mapNotNull { y ->
                if (initial[y].all { it == EMPTY }) y
                else null
            }
            return Space(initial.map { it.toTypedArray() }.toTypedArray(), maxX, maxY, rowIndexes, columnIndexes, weight)
        }
    }
}
