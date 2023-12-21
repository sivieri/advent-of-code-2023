package me.sivieri.aoc2023.day18

import me.sivieri.aoc2023.common.Direction
import java.lang.IllegalArgumentException

data class DigInstruction(
    val direction: Direction,
    val meters: Int,
    val color: String
) {
    companion object {
        fun fromString(input: String): DigInstruction {
            val (dir, m, c) = input.split(" ")
            val direction = when (dir) {
                "L" -> Direction.LEFT
                "R" -> Direction.RIGHT
                "U" -> Direction.UP
                "D" -> Direction.DOWN
                else -> throw IllegalArgumentException("No direction $dir")
            }
            return DigInstruction(
                direction,
                m.toInt(),
                c.substring(1, c.length - 1)
            )
        }
    }
}
