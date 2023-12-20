package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction
import org.jgrapht.graph.DefaultEdge

class DirectionalEdge(
    val direction: Direction
): DefaultEdge() {
    override fun toString(): String {
        return "DirectionalEdge(direction=$direction)"
    }
}
