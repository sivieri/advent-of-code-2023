package me.sivieri.aoc2023.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CuebGameTest {

    @Test
    fun `parse a line`() {
        val line = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
        val cubeGame = CuebGame.parse(line)
        val expected = CuebGame(
            4,
            listOf(
                mapOf(Cube.RED to 3, Cube.GREEN to 1, Cube.BLUE to 6),
                mapOf(Cube.RED to 6, Cube.GREEN to 3),
                mapOf(Cube.RED to 14, Cube.GREEN to 3, Cube.BLUE to 15),
            )
        )
        assertThat(cubeGame, `is`(expected))
    }

}