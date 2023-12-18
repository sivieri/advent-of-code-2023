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

}