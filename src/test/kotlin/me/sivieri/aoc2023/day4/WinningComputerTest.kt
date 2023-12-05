package me.sivieri.aoc2023.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class WinningComputerTest {

    @Test
    fun `part 1 example`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n").map { Scratchcard.parse(it) }
        val winningComputer = WinningComputer()
        val result = winningComputer.calculateTotalPoints(input)
        assertThat(result, `is`(13))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n").map { Scratchcard.parse(it) }
        val winningComputer = WinningComputer()
        val result = winningComputer.countScratchcards(input)
        assertThat(result, `is`(30))
    }

    @Test
    fun `part 2 corner case`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 87 83 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 31 11
        """.trimIndent().split("\n").map { Scratchcard.parse(it) }
        val winningComputer = WinningComputer()
        val result = winningComputer.countScratchcards(input)
        assertThat(result, `is`(44))
    }

}