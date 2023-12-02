package me.sivieri.aoc2023.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CubeGameSolverTest {

    @Test
    fun `part 1 example`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().split("\n")
        val cubeGameSolver = CubeGameSolver(input)
        val game = mapOf(Cube.RED to 12, Cube.GREEN to 13, Cube.BLUE to 14)
        val result = cubeGameSolver.validGames(game)
        assertThat(result, `is`(8))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().split("\n")
        val cubeGameSolver = CubeGameSolver(input)
        val result = cubeGameSolver.powerSum()
        assertThat(result, `is`(2286))
    }

}