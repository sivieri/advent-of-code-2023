package me.sivieri.aoc2023.day2

import me.sivieri.aoc2023.common.multiplyBy

data class CuebGame(
    val id: Int,
    val extractions: List<Map<Cube, Int>>
) {
    fun power(): Long = Cube.entries.toList().multiplyBy { cube ->
        extractions.map { it.getOrDefault(cube, 0) }.max().toLong()
    }

    companion object {
        fun parse(line: String): CuebGame {
            val (id, rest) = line.split(":")
            val extractions = rest
                .trim()
                .split(";")
                .map {
                    it
                        .trim()
                        .split(",")
                        .associate {
                            val (n, color) = it.trim().split(" ")
                            Cube.valueOf(color.uppercase()) to n.toInt()
                        }
                }
            return CuebGame(id.split(" ")[1].toInt(), extractions)
        }
    }
}