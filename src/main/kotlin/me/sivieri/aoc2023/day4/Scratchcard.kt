package me.sivieri.aoc2023.day4

data class Scratchcard(
    val id: Int,
    val winningNumbers: Set<Int>,
    val cardNumbers: Set<Int>
) {

    fun points(): Int = winningNumbers.intersect(cardNumbers).fold(0) { acc, _ ->
        if (acc == 0) 1
        else 2 * acc
    }

    companion object {
        fun parse(line: String): Scratchcard {
            val (id, numbers) = line.split(":")
            val (winning, own) = numbers.split("|")
            return Scratchcard(
                id.split("\\s+".toRegex())[1].toInt(),
                winning.trim().split("\\s+".toRegex()).map { it.toInt() }.toSet(),
                own.trim().split("\\s+".toRegex()).map { it.toInt() }.toSet(),
            )
        }
    }
}
