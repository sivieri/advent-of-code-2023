package me.sivieri.aoc2023.common

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun opposite(): Direction = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }
}
