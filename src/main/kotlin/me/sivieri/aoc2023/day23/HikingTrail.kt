package me.sivieri.aoc2023.day23

import me.sivieri.aoc2023.common.Coordinate2D

sealed interface HikingTrail {
    val coordinates: Coordinate2D
}

data class Path(private val c: Coordinate2D): HikingTrail {
    override val coordinates: Coordinate2D = c

    companion object {
        const val SYMBOL = '.'
    }
}

data class Forest(private val c: Coordinate2D): HikingTrail {
    override val coordinates: Coordinate2D = c

    companion object {
        const val SYMBOL = '#'
    }
}

data class Slope(
    private val c: Coordinate2D,
    val type: SlopeType
): HikingTrail {
    override val coordinates: Coordinate2D = c
}
