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

}
