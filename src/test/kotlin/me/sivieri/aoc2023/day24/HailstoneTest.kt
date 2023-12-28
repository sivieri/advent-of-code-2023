package me.sivieri.aoc2023.day24

import me.sivieri.aoc2023.common.Coordinate3DLong
import me.sivieri.aoc2023.common.between
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HailstoneTest {

    @Test
    fun `intersects 1`() {
        val a = Hailstone(Coordinate3DLong(19, 13, 30), Coordinate3DLong(-2, 1, -2))
        val b = Hailstone(Coordinate3DLong(18, 19, 22), Coordinate3DLong(-1, -1, -2))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, between(7.0, 27.0))
        assertTrue(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 2`() {
        val a = Hailstone(Coordinate3DLong(19, 13, 30), Coordinate3DLong(-2, 1, -2))
        val b = Hailstone(Coordinate3DLong(20, 25, 34), Coordinate3DLong(-2, -2, -4))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, between(7.0, 27.0))
        assertTrue(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 3`() {
        val a = Hailstone(Coordinate3DLong(19, 13, 30), Coordinate3DLong(-2, 1, -2))
        val b = Hailstone(Coordinate3DLong(12, 31, 28), Coordinate3DLong(-1, -2, -1))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, not(between(7.0, 27.0)))
        assertThat(y, between(7.0, 27.0))
        assertTrue(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 4`() {
        val a = Hailstone(Coordinate3DLong(19, 13, 30), Coordinate3DLong(-2, 1, -2))
        val b = Hailstone(Coordinate3DLong(20, 19, 15), Coordinate3DLong(1, -5, -3))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, between(7.0, 27.0))
        assertFalse(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 5`() {
        val a = Hailstone(Coordinate3DLong(18, 19, 22), Coordinate3DLong(-1, -1, -2))
        val b = Hailstone(Coordinate3DLong(20, 25, 34), Coordinate3DLong(-2, -2, -4))
        val res = a.intersects2D(b)
        assertNull(res)
        assertFalse(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 6`() {
        val a = Hailstone(Coordinate3DLong(18, 19, 22), Coordinate3DLong(-1, -1, -2))
        val b = Hailstone(Coordinate3DLong(12, 31, 28), Coordinate3DLong(-1, -2, -1))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, not(between(7.0, 27.0)))
        assertThat(y, not(between(7.0, 27.0)))
        assertTrue(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 7`() {
        val a = Hailstone(Coordinate3DLong(18, 19, 22), Coordinate3DLong(-1, -1, -2))
        val b = Hailstone(Coordinate3DLong(20, 19, 15), Coordinate3DLong(1, -5, -3))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, between(7.0, 27.0))
        assertFalse(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 8`() {
        val a = Hailstone(Coordinate3DLong(20, 25, 34), Coordinate3DLong(-2, -2, -4))
        val b = Hailstone(Coordinate3DLong(12, 31, 28), Coordinate3DLong(-1, -2, -1))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, not(between(7.0, 27.0)))
        assertThat(y, not(between(7.0, 27.0)))
        assertTrue(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 9`() {
        val a = Hailstone(Coordinate3DLong(20, 25, 34), Coordinate3DLong(-2, -2, -4))
        val b = Hailstone(Coordinate3DLong(20, 19, 15), Coordinate3DLong(1, -5, -3))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, between(7.0, 27.0))
        assertFalse(a.intersectsInFuture2D(b))
    }

    @Test
    fun `intersects 10`() {
        val a = Hailstone(Coordinate3DLong(12, 31, 28), Coordinate3DLong(-1, -2, -1))
        val b = Hailstone(Coordinate3DLong(20, 19, 15), Coordinate3DLong(1, -5, -3))
        val (x, y) = a.intersects2D(b)!!
        assertThat(x, between(7.0, 27.0))
        assertThat(y, not(between(7.0, 27.0)))
        assertFalse(a.intersectsInFuture2D(b))
    }

}