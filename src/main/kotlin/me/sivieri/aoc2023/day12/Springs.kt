package me.sivieri.aoc2023.day12

class Springs(data: List<String>) {

    private val springs = data.map { SpringStatus.parse(it) }

    fun findAllArrangements(extended: Boolean = false): Long {
        var counter = 0
        return springs.fold(0L) { acc, spring ->
            if (counter % 10 == 0) println("${counter++}")
            acc + spring.findArrangements(extended)
        }
    }

}