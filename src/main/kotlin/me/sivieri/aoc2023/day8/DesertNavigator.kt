package me.sivieri.aoc2023.day8

import me.sivieri.aoc2023.common.Math.lcm

class DesertNavigator(data: String) {

    private val desertDirections: Array<DesertDirection>
    private val root: MapInstruction?
    private val nodes: Map<String, MapInstruction>

    init {
        val (directions, map) = data.split("\n\n")
        this.desertDirections = directions.map { if (it == 'L') DesertDirection.LEFT else DesertDirection.RIGHT }.toTypedArray()
        val lines = map
            .split("\n")
            .filter { it.isNotBlank() }
            .map {
                val (root, children) = it.split("=")
                val (left, right) = children.trim(' ', '(', ')').split(",")
                Pair(root.trim(), Pair(left.trim(), right.trim()))
            }
        nodes = lines
            .flatMap { listOf(it.first, it.second.first, it.second.second) }
            .toSet()
            .associateWith { MapInstruction(it) }
        root = nodes[START]
        lines.forEach { (d, c) ->
            val (l, r) = c
            val destination = nodes[d]!!
            val left = nodes[l]!!
            val right = nodes[r]!!
            destination.left = left
            destination.right = right
        }
    }

    fun countSteps(): Int = countSteps(this.root!!) { it == END }

    private fun countSteps(root: MapInstruction, endFunction: (String) -> Boolean): Int {
        var i = 0
        var result = 0
        var current = root
        while (!endFunction(current.value)) {
            current = if (desertDirections[i] == DesertDirection.LEFT) current.left!!
            else current.right!!
            i = (i + 1) % desertDirections.size
            result++
        }
        return result
    }

    fun countStepsInParallel(): Long {
        val currents = nodes
            .keys
            .filter { it[2] == SINGLE_START }
            .map { nodes[it]!! }
            .map { countSteps(it) { it[2] == SINGLE_END }.toLong() }
        return currents.lcm()
    }

    companion object {
        private const val START = "AAA"
        private const val END = "ZZZ"
        private const val SINGLE_START = 'A'
        private const val SINGLE_END = 'Z'
    }

}