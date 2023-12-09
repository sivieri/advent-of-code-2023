package me.sivieri.aoc2023.day9

data class ValueHistory(
    val history: List<Int>
) {

    fun findNextValue(): Int {
        val processed = mutableListOf(history.toMutableList())
        while (!processed.last().all { it == 0 }) {
            processed.add(processed.last().zipWithNext().map { it.second - it.first }.toMutableList())
        }
        processed.last().add(0)
        processed.reversed().zipWithNext().forEach { (prev, cur) ->
            val missing = prev.last() + cur.last()
            cur.add(missing)
        }
        return processed.first().last()
    }

    fun findPrevValue(): Int {
        val processed = mutableListOf(history.toMutableList())
        while (!processed.last().all { it == 0 }) {
            processed.add(processed.last().zipWithNext().map { it.second - it.first }.toMutableList())
        }
        processed.last().add(0, 0)
        processed.reversed().zipWithNext().forEach { (prev, cur) ->
            val missing = cur.first() - prev.first()
            cur.add(0, missing)
        }
        return processed.first().first()
    }

}
