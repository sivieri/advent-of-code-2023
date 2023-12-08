package me.sivieri.aoc2023.day8

data class MapInstruction(
    val value: String,
    var left: MapInstruction? = null,
    var right: MapInstruction? = null
)
