package me.sivieri.aoc2023.day3

import java.util.regex.Pattern

class EngineSchematic(input: String) {

    private val data = input.split("\n").map { it.trim() }.filterNot { it.isBlank() }
    private val parsedParts = data.flatMapIndexed { index, line ->
        val matcher = pattern.matcher(line)
        matcher.results().iterator().asSequence().toList().map {
            EnginePart(
                it.group().toInt(),
                index,
                it.start(),
                it.end(),
                false
            )
        }
    }
    private val parsedGears = data.flatMapIndexed { n, line ->
        line.mapIndexedNotNull { index, c ->  if (c == GEAR) Gear(n, index, emptyList()) else null }
    }

    fun sumParts(): Long {
        val matched = parsedParts
            .map { e ->
                val lines = setOf(
                    e.line,
                    (e.line - 1).coerceAtLeast(0),
                    (e.line + 1).coerceAtMost(data.size - 1)
                ).map { data[it] }
                val matched = lines.any { line ->
                    val startIndex = (e.start - 1).coerceAtLeast(0)
                    val endIndex = (e.end + 1).coerceAtMost(line.length)
                    line.substring(startIndex, endIndex).any { !it.isDigit() && it != DOT }
                }
                e.copy(adj = matched)
            }
            .filter { it.adj }
        return matched
            .fold(0L) { acc, e ->
                acc + e.value
            }
    }

    fun gearsRatio(): Long {
        val matched = parsedGears
            .map { g ->
                val minLine = (g.line - 1).coerceAtLeast(0)
                val maxLine = (g.line + 1).coerceAtMost(data.size - 1)
                val minRow = (g.index - 1).coerceAtLeast(0)
                val maxRow = (g.index + 1).coerceAtMost(data[0].length - 1)
                val adj = parsedParts.filter { e ->
                    e.line in minLine..maxLine &&
                    (minRow..maxRow).any { it in e.start until e.end }
                }
                g.copy(
                    numbers = adj.map { it.value }
                )
            }
            .filter { it.numbers.size == 2 }
        return matched.fold(0L) { acc, g ->
            acc + g.numbers[0] * g.numbers[1]
        }
    }

    companion object {
        private const val DOT = '.'
        private const val GEAR = '*'
        private val pattern = Pattern.compile("([0-9]+)")
    }

}