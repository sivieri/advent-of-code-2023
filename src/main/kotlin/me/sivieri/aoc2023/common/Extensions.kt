package me.sivieri.aoc2023.common

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.jgrapht.Graph
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import java.util.*
import java.util.Collections.swap

internal fun String.toIntList(): List<Int> = this.split(",").map { it.toInt() }

// https://gist.github.com/dmdrummond/4b1d8a4f024183375f334a5f0a984718
internal fun <T> List<T>.permutations(): List<List<T>> {
    val retVal: MutableList<List<T>> = mutableListOf()

    fun generate(k: Int, list: List<T>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    swap(list, i, k - 1)
                } else {
                    swap(list, 0, k - 1)
                }
            }
        }
    }

    generate(this.count(), this.toList())
    return retVal
}

internal fun <A, B> cartesianProduct(a: List<A>, b: List<B>): List<Pair<A, B>> =
    a.flatMap { aElement -> b.map { bElement -> aElement to bElement } }

internal fun <A, B> Collection<A>.crossProduct(other: Collection<B>): List<Pair<A, B>> =
    this.flatMap { aElement -> other.map { bElement -> aElement to bElement } }

internal fun <T> List<T>.twoCombinations(): List<Pair<T, T>> =
    (0 until this.size).flatMap { i ->
        (i + 1 until this.size).map { j ->  Pair(this[i], this[j]) }
    }

internal fun <T> cartesianProductOfThree(
    a: Collection<T>,
    b: Collection<T>,
    c: Collection<T>,
): List<Triple<T, T, T>> =
    a.flatMap { aElement ->
        b.flatMap { bElement ->
            c.map { cElement -> Triple(aElement, bElement, cElement) }
        }
    }

internal fun <T> List<T>.zipWithIndex(f: (Int) -> Int): List<Pair<Int, T>> =
    this.mapIndexed { index, t -> Pair(f(index), t) }

internal fun <T> List<T>.multiplyBy(f: (T) -> Long): Long =
    this.fold(1) { acc, t ->
        acc * f(t)
    }

internal fun <T> List<T>.getMiddleElement(): T = this[(this.size - 1) / 2]

internal fun <T> Array<Array<T>>.stringRepresentation(cellSeparator: String, f: (T) -> String): String =
    this.joinToString("\n") { it.joinToString(cellSeparator) { f(it) }  }

internal fun String.isLowerCase(): Boolean = this.all { it in 'a'..'z' }

internal fun <V, E> Graph<V, E>.getOtherVertex(vertex: V, edge: E): V =
    when (vertex) {
        this.getEdgeSource(edge) -> this.getEdgeTarget(edge)
        this.getEdgeTarget(edge) -> this.getEdgeSource(edge)
        else -> throw IllegalArgumentException("Vertex $vertex is not connected to edge $edge")
    }

internal fun <K, V> Map<K, V>.combineWith(otherMap: Map<K, V>, f: (v1: V?, v2: V?) -> V): Map<K, V> =
    (this.keys + otherMap.keys).associateWith { key ->
        f(this[key], otherMap[key])
    }

internal suspend fun <K, V, R> Map<K, V>.pmap(f: suspend (Map.Entry<K, V>) -> R): List<R> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

internal suspend fun <T, R> List<T>.pmap(f: suspend (T) -> R): List<R> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

internal fun String.halve(): Pair<String, String> {
    val additionalOne = if (this.length % 2 == 0) 0 else 1
    return Pair(
        this.substring(0, this.length / 2 + additionalOne),
        this.substring(this.length / 2 + additionalOne, this.length)
    )
}

internal fun <T> List<T>.tail(): List<T> = when (this.size) {
    0 -> throw IllegalArgumentException("There is no tail in an empty list!")
    1 -> emptyList()
    else -> this.subList(1, this.size)
}

internal fun <K, V> Map<K, V>.findByValue(value: V): Map.Entry<K, V>? =
    this.entries.find { it.value == value }

internal fun <T> ArrayDeque<T>.removeFirstOrNull(): T? =
    try {
        this.removeFirst()
    }
    catch (e: NoSuchElementException) {
        null
    }

internal fun <T, R> Iterable<T>.firstNotNullOrNull(predicate: (T) -> R?): R? {
    for (element in this) {
        val value = predicate(element)
        if (value != null) return value
    }
    return null
}

internal fun <T> List<T>.replace(something: T, other: T): List<T> = this.map { if (it == something) other else it }

internal fun <T: Comparable<T>> Pair<T, T>.sort(): Pair<T, T> = if (this.first <= this.second) this.copy() else Pair(this.second, this.first)

internal fun String.repeat(n: Int, separator: Char) = (1..n).fold(this) { acc, _ ->
    acc + separator + this
}

internal fun <T> List<T>.repeat(n: Int): List<T> = (1..n).fold(this) { acc, _ ->
    acc + this
}

internal fun Pair<Int, Int>.sum() = this.first + this.second

internal fun List<Coordinate2D>.toPolygon(): Polygon = GeometryFactory()
    .createPolygon(this.plus(this.first()).map { Coordinate(it.x.toDouble(), it.y.toDouble()) }.toTypedArray())
