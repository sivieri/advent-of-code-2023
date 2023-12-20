package me.sivieri.aoc2023.common

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun orthogonal(): List<Direction> = when (this) {
        UP -> listOf(LEFT, RIGHT)
        DOWN -> listOf(LEFT, RIGHT)
        LEFT -> listOf(UP, DOWN)
        RIGHT -> listOf(UP, DOWN)
    }
}
