package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Coordinate3DDouble
import me.sivieri.aoc2023.common.Coordinate3DLong
import org.apache.commons.geometry.euclidean.threed.Vector3D
import org.apache.commons.geometry.euclidean.threed.line.Line3D
import org.apache.commons.geometry.euclidean.threed.line.Lines3D
import org.apache.commons.geometry.euclidean.twod.Line
import org.apache.commons.geometry.euclidean.twod.Lines
import org.apache.commons.geometry.euclidean.twod.Vector2D
import org.apache.commons.numbers.core.Precision
import kotlin.math.sign

data class Rock(
    val position: Coordinate3DDouble,
    val velocity: Coordinate3DDouble
)
