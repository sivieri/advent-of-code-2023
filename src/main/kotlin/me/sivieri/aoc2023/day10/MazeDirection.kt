package me.sivieri.aoc2023.day10

enum class MazeDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun opposite(): MazeDirection = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }
}