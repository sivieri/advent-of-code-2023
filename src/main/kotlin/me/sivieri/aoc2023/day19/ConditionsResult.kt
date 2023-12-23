package me.sivieri.aoc2023.day19

data class ConditionsResult(
    val x: List<Int>,
    val m: List<Int>,
    val a: List<Int>,
    val s: List<Int>
) {
    fun rating(): Long = x.count().toLong() * m.count().toLong() * a.count().toLong() * s.count().toLong()
}
