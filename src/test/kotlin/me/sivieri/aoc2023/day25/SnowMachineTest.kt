package me.sivieri.aoc2023.day25

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class SnowMachineTest {

    @Test
    fun `part 1 example`() {
        val input = """
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
        """.trimIndent().split("\n")
        val machine = SnowMachine(input)
        val result = machine.splitMachine()
        assertThat(result, `is`(54))
    }

}