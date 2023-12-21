package me.sivieri.aoc2023.day19

import java.lang.IllegalArgumentException

sealed class Rule(val destination: String) {
    abstract fun evaluate(part: MachinePart): Boolean
    abstract fun opposite(): Rule

    companion object {
        fun parse(s: String): Rule {
            val parts = s.split(":")
            return when {
                ">=" in parts[0] -> parts[0].split(">=").let { GreaterThanOrEqualsRule(it[0][0], it[1].toInt(), parts[1]) }
                '>' in parts[0] -> parts[0].split(">").let { GreaterThanRule(it[0][0], it[1].toInt(), parts[1]) }
                "<=" in parts[0] -> parts[0].split("<=").let { LessThanOrEqualsRule(it[0][0], it[1].toInt(), parts[1]) }
                '<' in parts[0] -> parts[0].split("<").let { LessThanRule(it[0][0], it[1].toInt(), parts[1]) }
                else -> DestinationRule(parts[0])
            }
        }
    }
}

class GreaterThanRule(
    val attr: Char,
    val value: Int,
    destination: String
): Rule(destination) {
    override fun evaluate(part: MachinePart): Boolean = when (attr) {
        'x' -> part.x > value
        'm' -> part.m > value
        'a' -> part.a > value
        's' -> part.s > value
        else -> throw IllegalArgumentException("Unknown attribute $attr")
    }

    override fun opposite(): Rule = LessThanOrEqualsRule(attr, value, destination)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GreaterThanRule

        if (attr != other.attr) return false
        if (value != other.value) return false
        if (destination != other.destination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attr.hashCode()
        result = 31 * result + value
        result = 31 * result + destination.hashCode()
        return result
    }

    override fun toString(): String {
        return "GreaterThanRule(attr=$attr, value=$value, destination=$destination)"
    }

}

class GreaterThanOrEqualsRule(
    val attr: Char,
    val value: Int,
    destination: String
): Rule(destination) {
    override fun evaluate(part: MachinePart): Boolean = when (attr) {
        'x' -> part.x >= value
        'm' -> part.m >= value
        'a' -> part.a >= value
        's' -> part.s >= value
        else -> throw IllegalArgumentException("Unknown attribute $attr")
    }

    override fun opposite(): Rule = LessThanRule(attr, value, destination)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GreaterThanOrEqualsRule

        if (attr != other.attr) return false
        if (value != other.value) return false
        if (destination != other.destination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attr.hashCode()
        result = 31 * result + value
        result = 31 * result + destination.hashCode()
        return result
    }

    override fun toString(): String {
        return "GreaterThanOrEqualsRule(attr=$attr, value=$value, destination=$destination)"
    }

}

class LessThanRule(
    val attr: Char,
    val value: Int,
    destination: String
): Rule(destination) {
    override fun evaluate(part: MachinePart): Boolean = when (attr) {
        'x' -> part.x < value
        'm' -> part.m < value
        'a' -> part.a < value
        's' -> part.s < value
        else -> throw IllegalArgumentException("Unknown attribute $attr")
    }

    override fun opposite(): Rule = GreaterThanOrEqualsRule(attr, value, destination)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LessThanRule

        if (attr != other.attr) return false
        if (value != other.value) return false
        if (destination != other.destination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attr.hashCode()
        result = 31 * result + value
        result = 31 * result + destination.hashCode()
        return result
    }

    override fun toString(): String {
        return "LessThanRule(attr=$attr, value=$value, destination=$destination)"
    }
}

class LessThanOrEqualsRule(
    val attr: Char,
    val value: Int,
    destination: String
): Rule(destination) {
    override fun evaluate(part: MachinePart): Boolean = when (attr) {
        'x' -> part.x <= value
        'm' -> part.m <= value
        'a' -> part.a <= value
        's' -> part.s <= value
        else -> throw IllegalArgumentException("Unknown attribute $attr")
    }

    override fun opposite(): Rule = GreaterThanRule(attr, value, destination)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LessThanOrEqualsRule

        if (attr != other.attr) return false
        if (value != other.value) return false
        if (destination != other.destination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attr.hashCode()
        result = 31 * result + value
        result = 31 * result + destination.hashCode()
        return result
    }

    override fun toString(): String {
        return "LessThanOrEqualsRule(attr=$attr, value=$value, destination=$destination)"
    }
}

class DestinationRule(destination: String): Rule(destination) {
    override fun evaluate(part: MachinePart): Boolean = true

    override fun opposite(): Rule = DestinationRule(destination)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DestinationRule

        return destination == other.destination
    }

    override fun hashCode(): Int {
        return destination.hashCode()
    }

    override fun toString(): String {
        return "DestinationRule(destination=$destination)"
    }
}
