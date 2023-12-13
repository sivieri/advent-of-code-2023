package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.common.twoCombinations
import me.sivieri.aoc2023.common.sort

class Observatory(data: String, weight: Int) {

    private val space = Space.fromString(data, weight)

    fun sumShortestPaths(): Long {
        val combinations = space.galaxies
            .twoCombinations()
            .map { it.sort() }
            .distinct()
        println("Found ${combinations.size} pairs")
        return combinations.mapIndexed { index, pair ->
            if (index % 1000 == 0) println(index)
            space.shortestPathLength(pair.first, pair.second)
        }.sum()
    }

}