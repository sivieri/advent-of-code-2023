package me.sivieri.aoc2023.day1

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CalibrationDocTest {

    @Test
    fun  `part 1 example`() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().split("\n")
        val calibrationDoc = CalibrationDoc()
        val result = calibrationDoc.getCalibrationTotal(input)
        assertThat(result, `is`(142))
    }

}