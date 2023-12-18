package me.sivieri.aoc2023.day15

class BoxQueue<T>(
    private val storage: MutableList<T> = mutableListOf()
): List<T> by storage {
    fun add(element: T) {
        if (isEmpty()) storage.add(element)
        else storage.add(size, element)
    }

    fun replace(element: T) {
        if (isNotEmpty()) {
            val index = storage.indexOf(element)
            storage.removeAt(index)
            storage.add(index, element)
        }
    }

    fun remove(element: T) {
        storage.remove(element)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoxQueue<*>

        return storage == other.storage
    }

    override fun hashCode(): Int {
        return storage.hashCode()
    }

    override fun toString(): String {
        return "BoxQueue(storage=$storage)"
    }

}