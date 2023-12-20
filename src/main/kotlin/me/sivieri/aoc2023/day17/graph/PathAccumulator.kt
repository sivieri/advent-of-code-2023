package me.sivieri.aoc2023.day17.graph

import me.sivieri.aoc2023.common.Direction

data class PathAccumulator(
    private val first: Direction? = null,
    private val second: Direction? = null,
    private val third: Direction? = null,
) {
    fun add(direction: Direction): PathAccumulator =
        if (first == null) PathAccumulator(first = direction)
        else if (second == null) PathAccumulator(first = direction, second = first)
        else PathAccumulator(first = direction, second = first, third = second)

    fun check(): Boolean {
        if (first != null && second != null && first.opposite() == second) return false
        if (first != null && second != null && third != null && first == second && second == third) return false
        return true
    }
}
