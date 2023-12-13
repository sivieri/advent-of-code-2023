package me.sivieri.aoc2023.day12

class Springs(data: List<String>) {

    private val springs = data.map { SpringStatus.parse(it) }

    fun findAllArrangements(): Int = springs.sumOf {
        val arr = it.findArrangements()
        println("Status $it, possibilities $arr")
        arr
    }

}