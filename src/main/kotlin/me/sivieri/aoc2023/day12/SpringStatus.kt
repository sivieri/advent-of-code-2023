package me.sivieri.aoc2023.day12

import me.sivieri.aoc2023.KCombinations
import me.sivieri.aoc2023.repeat
import me.sivieri.aoc2023.toIntList

data class SpringStatus(
    val positions: List<Char>,
    val numbers: List<Int>
) {
    fun findArrangements(extended: Boolean = false): Int = if (extended) findExtendedArrangements() else findStandardArrangements()

    private fun findStandardArrangements(): Int {
        val totalBroken = numbers.sum()
        val existingBroken = positions.count { it == BROKEN }
        val toBeSet = totalBroken - existingBroken
        val openPositions = positions.mapIndexedNotNull { i, c -> if (c == UNKNOWN) i else null }
        if (toBeSet == 0 && openPositions.isEmpty()) return 0
        if (toBeSet == 0 && openPositions.isNotEmpty()) return 1
        val combinations = KCombinations.enumKCombos(openPositions.toIntArray(), toBeSet)
        return combinations
            .count { combo ->
                val newPositions = positions.indices.map {
                    when (it) {
                        in combo -> BROKEN
                        in openPositions -> INTACT
                        else -> positions[it]
                    }
                }
                checkConsistency(newPositions, numbers)
            }
    }

    private fun findExtendedArrangements(): Int {
        val (positions, numbers) = Pair(
            positions.joinToString("").repeat(5, '?').toList(),
            numbers.repeat(5)
        )
        val totalBroken = numbers.sum()
        val existingBroken = positions.count { it == BROKEN }
        val toBeSet = totalBroken - existingBroken
        val openPositions = positions.mapIndexedNotNull { i, c -> if (c == UNKNOWN) i else null }
        if (toBeSet == 0 && openPositions.isEmpty()) return 0
        if (toBeSet == 0 && openPositions.isNotEmpty()) return 1
        val combinations = KCombinations.enumKCombos(openPositions.toIntArray(), toBeSet)
        return combinations
            .count { combo ->
                val newPositions = positions.indices.map {
                    when (it) {
                        in combo -> BROKEN
                        in openPositions -> INTACT
                        else -> positions[it]
                    }
                }
                checkConsistency(newPositions, numbers)
            }
    }

    companion object {
        private const val INTACT = '.'
        private const val BROKEN = '#'
        private const val UNKNOWN = '?'

        fun parse(line: String): SpringStatus {
            val (positions, numbers) = line.split(" ")
            return SpringStatus(positions.toList(), numbers.toIntList())
        }

        fun checkConsistency(positions: List<Char>, numbers: List<Int>): Boolean {
            val groups = positions
                .joinToString("")
                .split("\\.+".toRegex())
                .filter { it.isNotBlank() }
            if (groups.size != numbers.size) return false
            return groups.zip(numbers).all { it.first.length == it.second }
        }
    }
}
