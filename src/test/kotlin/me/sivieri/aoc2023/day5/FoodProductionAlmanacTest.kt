package me.sivieri.aoc2023.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class FoodProductionAlmanacTest {

    @Test
    fun `parse blocks`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()
        val almanac = FoodProductionAlmanac.parse(input)
        val expected = FoodProductionAlmanac(
            listOf(79, 14, 55, 13),
            listOf(
                AlmanacRangesInclusive(50, 50 + 1, 98, 98 + 1, 2),
                AlmanacRangesInclusive(52, 52 + 47, 50, 50 + 47, 48)
            ),
            listOf(
                AlmanacRangesInclusive(0, 0 + 36, 15, 15 + 36, 37),
                AlmanacRangesInclusive(37, 37 + 1, 52, 52 + 1, 2),
                AlmanacRangesInclusive(39, 39 + 14, 0, 0 + 14, 15)
            ),
            listOf(
                AlmanacRangesInclusive(49, 49 + 7, 53, 53 + 7, 8),
                AlmanacRangesInclusive(0, 0 + 41, 11, 11 + 41, 42),
                AlmanacRangesInclusive(42, 42 + 6, 0, 0 + 6, 7),
                AlmanacRangesInclusive(57, 57 + 3, 7, 7 + 3, 4)
            ),
            listOf(
                AlmanacRangesInclusive(88, 88 + 6, 18, 18 + 6, 7),
                AlmanacRangesInclusive(18, 18 + 69, 25, 25 + 69, 70)
            ),
            listOf(
                AlmanacRangesInclusive(45, 45 + 22, 77, 77 + 22, 23),
                AlmanacRangesInclusive(81, 81 + 18, 45, 45 + 18, 19),
                AlmanacRangesInclusive(68, 68 + 12, 64, 64 + 12, 13)
            ),
            listOf(
                AlmanacRangesInclusive(0, 0 + 0, 69, 69 + 0, 1),
                AlmanacRangesInclusive(1, 1 + 68, 0, 0 + 68, 69),
            ),
            listOf(
                AlmanacRangesInclusive(60, 60 + 36, 56, 56 + 36, 37),
                AlmanacRangesInclusive(56, 56 + 3, 93, 93 + 3, 4),
            )
        )
        assertThat(almanac, `is`(expected))
    }

}