package me.sivieri.aoc2023.day2

data class CuebGame(
    val id: Int,
    val extractions: List<Map<Cube, Int>>
) {
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
                            Cube.valueOf(color.toUpperCase()) to n.toInt()
                        }
                }
            return CuebGame(id.split(" ")[1].toInt(), extractions)
        }
    }
}