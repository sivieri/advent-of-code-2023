package me.sivieri.aoc2023.common

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import org.junit.Test

class DijkstraShortestPathWithConstraintTest {

    @Test
    fun `standard shortest path`() {
        val graph = SimpleGraph<TestVertex, DefaultEdge>(DefaultEdge::class.java)
        val a = TestVertex("a", 1)
        val b = TestVertex("b", 2)
        val c = TestVertex("c", 3)
        val d = TestVertex("d", 4)
        val e = TestVertex("e", 5)
        graph.addVertex(a)
        graph.addVertex(b)
        graph.addVertex(c)
        graph.addVertex(d)
        graph.addVertex(e)
        graph.addEdge(a, b)
        graph.addEdge(a, c)
        graph.addEdge(b, d)
        graph.addEdge(d, e)
        graph.addEdge(c, e)
        val sp = DijkstraShortestPath(graph)
        val result = sp.getPathWeight(a, e).toInt()
        assertThat(result, `is`(2))
    }

    @Test
    fun `weighted shortest path`() {
        val graph = SimpleGraph<TestVertex, DefaultEdge>(DefaultEdge::class.java)
        val a = TestVertex("a", 1)
        val b = TestVertex("b", 2)
        val c = TestVertex("c", 3)
        val d = TestVertex("d", 4)
        val e = TestVertex("e", 5)
        graph.addVertex(a)
        graph.addVertex(b)
        graph.addVertex(c)
        graph.addVertex(d)
        graph.addVertex(e)
        graph.addEdge(a, b)
        graph.addEdge(a, c)
        graph.addEdge(b, d)
        graph.addEdge(d, e)
        graph.addEdge(c, e)
        val sp = DijkstraShortestPathWithConstraint(
            graph,
            { _, _, _, _ -> 0 },
            { _ -> true },
            0
        )
        val result = sp.getPathWeight(a, e).toInt()
        assertThat(result, `is`(8))
    }

    @Test
    fun `weighted shortest path with constraint`() {
        val graph = SimpleGraph<TestVertex, DefaultEdge>(DefaultEdge::class.java)
        val a = TestVertex("a", 1)
        val b = TestVertex("b", 1)
        val c = TestVertex("c", 1)
        val d = TestVertex("d", 1)
        val e = TestVertex("e", 1)
        val f = TestVertex("f", 3)
        val g = TestVertex("g", 10)
        graph.addVertex(a)
        graph.addVertex(b)
        graph.addVertex(c)
        graph.addVertex(d)
        graph.addVertex(e)
        graph.addVertex(f)
        graph.addVertex(g)
        graph.addEdge(a, b)
        graph.addEdge(b, c)
        graph.addEdge(c, d)
        graph.addEdge(d, e)
        graph.addEdge(e, f)
        graph.addEdge(a, g)
        graph.addEdge(g, f)
        val sp = DijkstraShortestPathWithConstraint(
            graph,
            { _, _, _, cost -> cost + 1 },
            { cost -> cost <= 3 },
            0
        )
        val result = sp.getPathWeight(a, f).toInt()
        assertThat(result, `is`(13))
    }

    companion object {
        private data class TestVertex(val name: String, override val weight: Int): VertexWithWeight
    }

}