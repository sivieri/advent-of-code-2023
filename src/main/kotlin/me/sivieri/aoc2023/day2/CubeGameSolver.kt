package me.sivieri.aoc2023.day2

class CubeGameSolver(input: List<String>) {

    private val games = input.map { CuebGame.parse(it) }

    fun validGames(content: Map<Cube, Int>): Int = games
        .filter { game ->
            game.extractions.all { extraction ->
                content.all { (color, n) ->
                    extraction.getOrDefault(color, 0) <= n
                }
            }
        }
        .sumOf { it.id }

    fun powerSum(): Long = games.sumOf { it.power() }

}