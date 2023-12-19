package me.sivieri.aoc2023.day16

import java.lang.IllegalArgumentException

enum class Mirror(val symbol: Char) {
    EMPTY('.'),
    HORIZONTAL_SPLITTER('-'),
    VERTICAL_SPLITTER('|'),
    LEFT_ANGLE('/'),
    RIGHT_ANGLE('\\');

    companion object {
        fun fromSymbol(symbol: Char): Mirror = entries.find { it.symbol == symbol }
            ?: throw IllegalArgumentException("Unknown mirror with symbol $symbol")
    }
}