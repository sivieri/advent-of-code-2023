package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.twoCombinations

class Hail(input: List<String>) {

    private val hailstones: List<Hailstone> = input.map { Hailstone.parse(it) }

    fun countIntersectingHailstones2D(min: Long, max: Long): Int = hailstones
        .twoCombinations()
        .count { (a, b) ->
            val pair = a.intersects2D(b)
            val future = a.intersectsInFuture(b)
            future && pair != null && pair.first >= min && pair.first <= max && pair.second >= min && pair.second <= max
        }

}