package me.sivieri.aoc2023.day22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class SandBrickStackTest {

    @Test
    @Ignore
    fun `part 1 example`() {
        val input = """
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
        """.trimIndent().split("\n")
        val stack = BrickStack(input)
        val result = stack.countDisposableBricks()
        assertThat(result, `is`(5))
    }

}
