package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.common.Coordinate2D

data class Galaxy(
    val index: Int,
    val position: Coordinate2D
): Comparable<Galaxy> {
    override fun compareTo(other: Galaxy): Int = this.index.compareTo(other.index)
}
