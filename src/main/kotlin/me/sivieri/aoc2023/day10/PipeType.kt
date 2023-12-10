package me.sivieri.aoc2023.day10

import me.sivieri.aoc2023.day10.AnimalDirection.*
import java.lang.IllegalArgumentException

enum class PipeType(val symbol: Char) {
    VERTICAL('|'),
    HORIZONTAL('-'),
    BEND_UR('L'),
    BEND_UL('J'),
    BEND_DR('F'),
    BEND_DL('7'),
    EMPTY('.');

    // This would be a class value, but we have to avoid self references in the constructor
    fun destinations(): Map<AnimalDirection, List<PipeType>> = when (this) {
        VERTICAL -> mapOf(
            DOWN to listOf(VERTICAL, BEND_UL, BEND_UR),
            UP to listOf(VERTICAL, BEND_DL, BEND_DR)
        )
        HORIZONTAL -> mapOf(
            LEFT to listOf(HORIZONTAL, BEND_UR, BEND_DR),
            RIGHT to listOf(HORIZONTAL, BEND_UL, BEND_DL)
        )
        BEND_UR -> mapOf(
            UP to listOf(VERTICAL, BEND_DL, BEND_DR),
            RIGHT to listOf(HORIZONTAL, BEND_UL, BEND_DL)
        )
        BEND_UL -> mapOf(
            UP to listOf(VERTICAL, BEND_DL, BEND_DR),
            LEFT to listOf(HORIZONTAL, BEND_UR, BEND_DR)
        )
        BEND_DR -> mapOf(
            DOWN to listOf(VERTICAL, BEND_UL, BEND_UR),
            RIGHT to listOf(HORIZONTAL, BEND_UL, BEND_DL)
        )
        BEND_DL -> mapOf(
            DOWN to listOf(VERTICAL, BEND_UL, BEND_UR),
            LEFT to listOf(HORIZONTAL, BEND_UR, BEND_DR)
        )
        EMPTY -> emptyMap()
    }

    fun nextDirection(from: AnimalDirection): AnimalDirection = when {
        this == VERTICAL && from == UP -> DOWN
        this == VERTICAL && from == DOWN -> UP
        this == HORIZONTAL && from == LEFT -> RIGHT
        this == HORIZONTAL && from == RIGHT -> LEFT
        this == BEND_UR && from == UP -> RIGHT
        this == BEND_UR && from == RIGHT -> UP
        this == BEND_UL && from == UP -> LEFT
        this == BEND_UL && from == LEFT -> UP
        this == BEND_DR && from == DOWN -> RIGHT
        this == BEND_DR && from == RIGHT -> DOWN
        this == BEND_DL && from == DOWN -> LEFT
        this == BEND_DL && from == LEFT -> DOWN
        else -> throw IllegalArgumentException("No direction home")
    }

    companion object {
        fun fromSymbol(symbol: Char): PipeType = entries.find { it.symbol == symbol }
            ?: throw IllegalArgumentException("Symbol $symbol is not valid")
    }
}