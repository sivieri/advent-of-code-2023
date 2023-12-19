package me.sivieri.aoc2023.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BeamContraptionTest {

    @Test
    fun `part 1 example`() {
        val input = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent()
        val contraption = BeamContraption(input)
        val result = contraption.countEnergizedCells()
        assertThat(result, `is`(46))
    }

}