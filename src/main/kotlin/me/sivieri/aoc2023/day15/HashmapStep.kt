package me.sivieri.aoc2023.day15

sealed class HashmapStep(val label: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddStep

        return label == other.label
    }

    override fun hashCode(): Int {
        return label.hashCode()
    }
}

class RemoveStep(
    label: String
): HashmapStep(label) {
    override fun toString(): String = "$label-"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as HashmapStep

        return label == other.label
    }

    override fun hashCode(): Int {
        return label.hashCode()
    }
}

class AddStep(
    label: String,
    val lens: Int
): HashmapStep(label) {
    override fun toString(): String = "$label=$lens"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as HashmapStep

        return label == other.label
    }

    override fun hashCode(): Int {
        return label.hashCode()
    }
}
