package me.sivieri.aoc2023.day5

data class AlmanacRangesInclusive(
    val destinationRangeStart: Long,
    val destinationRangeEnd: Long,
    val sourceRangeStart: Long,
    val sourceRangeEnd: Long,
    val rangeLength: Long
) {

    fun contains(n: Long) = n in sourceRangeStart..sourceRangeEnd

    fun findDestination(n: Long) = if (contains(n)) n + (destinationRangeStart - sourceRangeStart) else null

    companion object {
        fun parse(data: String): AlmanacRangesInclusive {
            val numbers = data.split(" ").map { it.toLong() }
            return AlmanacRangesInclusive(
                numbers[0],
                numbers[0] + numbers[2] - 1,
                numbers[1],
                numbers[1] + numbers[2] - 1,
                numbers[2]
            )
        }
    }
}
