package me.sivieri.aoc2023.day3

import java.util.regex.Pattern

class EngineSchematic(input: String) {

    private val data = input.split("\n").map { it.trim() }.filterNot { it.isBlank() }

    fun sumParts(): Long {
        val parsed = data.flatMapIndexed { index, line ->
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
        val matched = parsed
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

    companion object {
        private const val DOT = '.'
        private val pattern = Pattern.compile("([0-9]+)")
    }

}