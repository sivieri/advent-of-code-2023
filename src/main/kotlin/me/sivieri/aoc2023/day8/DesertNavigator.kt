package me.sivieri.aoc2023.day8

class DesertNavigator(data: String) {

    private val directions: Array<Direction>
    private val root: MapInstruction

    init {
        val (directions, map) = data.split("\n\n")
        this.directions = directions.map { if (it == 'L') Direction.LEFT else Direction.RIGHT }.toTypedArray()
        val lines = map
            .split("\n")
            .filter { it.isNotBlank() }
            .map {
                val (root, children) = it.split("=")
                val (left, right) = children.trim(' ', '(', ')').split(",")
                Pair(root.trim(), Pair(left.trim(), right.trim()))
            }
        val nodes = lines
            .flatMap { listOf(it.first, it.second.first, it.second.second) }
            .toSet()
            .associateWith { MapInstruction(it) }
        root = nodes[START]!!
        lines.forEach { (d, c) ->
            val (l, r) = c
            val destination = nodes[d]!!
            val left = nodes[l]!!
            val right = nodes[r]!!
            destination.left = left
            destination.right = right
        }
    }

    fun countSteps(): Int {
        var i = 0
        var result = 0
        var current = root
        while (current.value != END) {
            current = if (directions[i] == Direction.LEFT) current.left!!
            else current.right!!
            i = (i + 1) % directions.size
            result++
        }
        return result
    }

    companion object {
        private const val START = "AAA"
        private const val END = "ZZZ"
    }

}