package me.sivieri.aoc2023.day17

import me.sivieri.aoc2023.common.Coordinate2D
import me.sivieri.aoc2023.common.Direction
import me.sivieri.aoc2023.day17.graph.NextVertex
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CityMapTest {

    @Test
    fun `part 1 example`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent()
        val map = CityMap(input)
        val loss = map.calculateMinHeatLoss()
        assertThat(loss, `is`(102))
    }

    @Test
    fun `neighbors finder`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent()
        val map = CityMap(input)
        val neighbors = map.findUltraNeighbors(
            CityBlock(Coordinate2D(0, 0), 2),
            Direction.DOWN
        )
        // 2 4 1 3 4 3 2 3 1 1 3
        val first = CityBlock(Coordinate2D(1, 0), 4)
        val second = CityBlock(Coordinate2D(2, 0), 1)
        val third = CityBlock(Coordinate2D(3, 0), 3)
        val fourth = CityBlock(Coordinate2D(4, 0), 4)
        val fifth = CityBlock(Coordinate2D(5, 0), 3)
        val sixth = CityBlock(Coordinate2D(6, 0), 2)
        val seventh = CityBlock(Coordinate2D(7, 0), 3)
        val eighth = CityBlock(Coordinate2D(8, 0), 1)
        val ninth = CityBlock(Coordinate2D(9, 0), 1)
        val tenth = CityBlock(Coordinate2D(10, 0), 3)
        val expected = listOf(
            NextVertex(
                fourth,
                Direction.RIGHT,
                listOf(first, second, third, fourth)
            ),
            NextVertex(
                fifth,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth)
            ),
            NextVertex(
                sixth,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth, sixth)
            ),
            NextVertex(
                seventh,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth, sixth, seventh)
            ),
            NextVertex(
                eighth,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth, sixth, seventh, eighth)
            ),
            NextVertex(
                ninth,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)
            ),
            NextVertex(
                tenth,
                Direction.RIGHT,
                listOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth)
            )
        )
        assertThat(neighbors, `is`(expected))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent()
        val map = CityMap(input)
        val loss = map.calculateUltraMinHeatLoss()
        assertThat(loss, `is`(94))
    }

    @Test
    fun `part 2 another example`() {
        val input = """
            111111111111
            999999999991
            999999999991
            999999999991
            999999999991
        """.trimIndent()
        val map = CityMap(input)
        val loss = map.calculateUltraMinHeatLoss()
        assertThat(loss, `is`(71))
    }

}