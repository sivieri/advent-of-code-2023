package me.sivieri.aoc2023.day18

import me.sivieri.aoc2023.common.Direction
import java.lang.IllegalArgumentException

data class DigInstruction(
    val direction: Direction,
    val meters: Int,
    val color: String
) {
    fun extendedInstruction(): DigInstruction {
        val meters = color.substring(1, 6).toInt(radix = 16)
        val direction = when(color[6]) {
            '0' -> Direction.RIGHT
            '1' -> Direction.DOWN
            '2' -> Direction.LEFT
            '3' -> Direction.UP
            else -> throw IllegalArgumentException("No direction ${color[5]}")
        }
        return DigInstruction(direction, meters, "")
    }

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
