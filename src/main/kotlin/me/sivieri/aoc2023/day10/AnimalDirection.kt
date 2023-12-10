package me.sivieri.aoc2023.day10

enum class AnimalDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun opposite(): AnimalDirection = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }
}