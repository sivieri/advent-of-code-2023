package me.sivieri.aoc2023.day23

import me.sivieri.aoc2023.common.Coordinate2D
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedGraph

class HikingTrailsNoSlopes(input: String) {

    private val trails = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = trails[0].size
    private val maxY = trails.size
    private val start = Coordinate2D(trails[0].indexOf(Path.SYMBOL), 0)
    private val end = Coordinate2D(trails[maxY - 1].indexOf(Path.SYMBOL),maxY - 1)
    private val graph = SimpleDirectedGraph<HikingTrail, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    private val blocks: Map<Coordinate2D, HikingTrail> = (0 until maxY)
        .flatMap { y ->
            (0 until maxX).map { x ->
                when (trails[y][x]) {
                    Path.SYMBOL -> Path(Coordinate2D(x, y))
                    Forest.SYMBOL -> Forest(Coordinate2D(x, y))
                    SlopeType.UP.symbol -> Slope(Coordinate2D(x, y), SlopeType.UP)
                    SlopeType.DOWN.symbol -> Slope(Coordinate2D(x, y), SlopeType.DOWN)
                    SlopeType.LEFT.symbol -> Slope(Coordinate2D(x, y), SlopeType.LEFT)
                    SlopeType.RIGHT.symbol -> Slope(Coordinate2D(x, y), SlopeType.RIGHT)
                    else -> throw IllegalArgumentException("Unknown type ${trails[y][x]}")
                }
            }
        }
        .associateBy { it.coordinates }

    init {
        blocks.values.forEach {
            if (it !is Forest) graph.addVertex(it)
        }
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val c = Coordinate2D(x, y)
                val b = blocks[c]!!
                val neighbors = when (b) {
                    is Forest -> emptyList()
                    is Path -> setOf(
                        Coordinate2D((x - 1).coerceAtLeast(0), y),
                        Coordinate2D((x + 1).coerceAtMost(maxX - 1), y),
                        Coordinate2D(x, (y - 1).coerceAtLeast(0)),
                        Coordinate2D(x, (y + 1).coerceAtMost(maxY - 1))
                    ).minus(c)
                    is Slope -> {
                        setOf(
                            Coordinate2D((x - 1).coerceAtLeast(0), y),
                            Coordinate2D((x + 1).coerceAtMost(maxX - 1), y),
                            Coordinate2D(x, (y - 1).coerceAtLeast(0)),
                            Coordinate2D(x, (y + 1).coerceAtMost(maxY - 1))
                        ).minus(c)
                    }
                }
                neighbors.forEach { n ->
                    val source = blocks[c]!!
                    val dest = blocks[n]!!
                    if (dest !is Forest) {
                        graph.addEdge(source, dest)
                    }
                }
            }
        }
    }

    fun findLongestPath(): Int {
        val r = dfs(blocks[start]!!, emptyList(), emptyList())
        val f = r.filter { it.contains(blocks[end]!!) }
        return f.maxOf { it.size }
    }

    private fun dfs(v: HikingTrail, seen: List<HikingTrail>, path: List<HikingTrail>): List<List<HikingTrail>> {
        val newSeen = seen.plus(v)
        val others = graph.outgoingEdgesOf(v).map { graph.getEdgeTarget(it) }
        val paths = mutableListOf<List<HikingTrail>>()
        others.forEach { t ->
            if (t !in seen) {
                val newPath = path.plus(t)
                paths.add(newPath)
                paths.addAll(dfs(t, newSeen, newPath))
            }
        }
        return paths.toList()
    }

}