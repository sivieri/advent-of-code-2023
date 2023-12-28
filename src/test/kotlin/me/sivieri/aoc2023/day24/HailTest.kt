package me.sivieri.aoc2023.day24

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class HailTest {

    @Test
    fun `part 1 example`() {
        val input = """
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
        """.trimIndent().split("\n")
        val hail = Hail(input)
        val result = hail.countIntersectingHailstones2D(7, 27)
        assertThat(result, `is`(2))
    }

}