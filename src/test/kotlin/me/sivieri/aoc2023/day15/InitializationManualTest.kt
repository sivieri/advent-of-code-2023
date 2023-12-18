package me.sivieri.aoc2023.day15

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class InitializationManualTest {

    @Test
    fun `part 1 example`() {
        val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        val manual = InitializationManual(input)
        val result = manual.sumInstructions()
        assertThat(result, `is`(1320))
    }

    @Test
    fun `part 2 boxes`() {
        val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        val manual = InitializationManual(input)
        val boxes = manual.fillAndGetBoxes()
        val expected = mapOf(0 to BoxQueue(mutableListOf(AddStep("rn", 1), AddStep("cm", 2)))) +
            listOf(1, 2).associateWith { BoxQueue() } +
            listOf(3 to BoxQueue(mutableListOf(AddStep("ot", 7), AddStep("ab", 5), AddStep("pc", 6)))) +
            (4..255).associateWith { BoxQueue() }
        assertThat(boxes, `is`(expected))
    }

    @Test
    fun `part 2 example`() {
        val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        val manual = InitializationManual(input)
        val power = manual.focusingPower()
        assertThat(power, `is`(145))
    }

}