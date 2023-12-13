package me.sivieri.aoc2023.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SpringStatusTest {

    @Test
    fun `correct consistency`() {
        val springStatus = SpringStatus(".###..##.#..".toList(), listOf(3, 2, 1))
        assertTrue(SpringStatus.checkConsistency(springStatus.positions, springStatus.numbers))
    }

    @Test
    fun `incorrect consistency`() {
        val springStatus = SpringStatus(".###..##.#..".toList(), listOf(3, 4, 1))
        assertFalse(SpringStatus.checkConsistency(springStatus.positions, springStatus.numbers))
    }

    @Test
    fun `arrangements example 1`() {
        val springStatus = SpringStatus("???.###".toList(), listOf(1, 1, 3))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(1))
    }

    @Test
    fun `arrangements example 2`() {
        val springStatus = SpringStatus(".??..??...?##.".toList(), listOf(1, 1, 3))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(4))
    }

    @Test
    fun `arrangements example 3`() {
        val springStatus = SpringStatus("?###????????".toList(), listOf(3, 2, 1))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(10))
    }

    @Test
    fun `arrangements example 4`() {
        val springStatus = SpringStatus(".??#?#.".toList(), listOf(3))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(1))
    }

    @Test
    fun `arrangements example 5`() {
        val springStatus = SpringStatus("#??#".toList(), listOf(2))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(1))
    }

    @Test
    fun `arrangements example 6`() {
        val springStatus = SpringStatus("??????????#.".toList(), listOf(1, 5))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(5))
    }

    @Test
    fun `arrangements example 7`() {
        val springStatus = SpringStatus("?##?.#..?#??#??#?.".toList(), listOf(2, 1, 1, 1, 1))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(1))
    }

    @Test
    fun `arrangements example 8`() {
        val springStatus = SpringStatus("??#??.#?...".toList(), listOf(1, 1))
        val arrangements = springStatus.findArrangements()
        assertThat(arrangements, `is`(1))
    }

}