package me.sivieri.aoc2023.day11

import me.sivieri.aoc2023.combinations
import me.sivieri.aoc2023.sort

class Observatory(data: String) {

    private val space = Space.fromString(data)

    fun sumShortestPaths(): Int {
        val combinations = space.galaxies
            .combinations()
            .map { it.sort() }
            .distinct()
        println("Found ${combinations.size} pairs")
        return combinations.mapIndexed { index, pair ->
            if (index % 1000 == 0) println(index)
            space.shortestPathLength(pair.first, pair.second)
        }.sum()
    }

}