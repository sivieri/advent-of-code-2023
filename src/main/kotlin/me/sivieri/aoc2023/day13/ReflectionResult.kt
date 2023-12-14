package me.sivieri.aoc2023.day13

data class ReflectionResult(
    val value: Int,
    val remaining: Int,
    val position: ReflectionPosition,
    val changed: Boolean
)
