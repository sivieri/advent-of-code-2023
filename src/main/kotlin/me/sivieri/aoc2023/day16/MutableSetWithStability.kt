package me.sivieri.aoc2023.day16

class MutableSetWithStability<T>(
    private val maxSize: Int
) {

    private val storage: MutableSet<T> = mutableSetOf()
    private var changed: Boolean = false
    private var addCalls: Int = 0

    val size: Int
        get() = storage.size

    fun add(element: T) {
        changed = storage.add(element)
        addCalls++
    }

    fun isStable(): Boolean = !changed && addCalls >= 1000 * maxSize

}