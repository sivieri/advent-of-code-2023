package me.sivieri.aoc2023.day19

sealed class Condition(
    val attr: Char,
    val value: Int
) {
    abstract fun opposite(): Condition
    abstract fun test(value: Int): Boolean
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Condition) return false

        if (attr != other.attr) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attr.hashCode()
        result = 31 * result + value
        return result
    }
}

class GreaterThanCondition(
    attr: Char,
    value: Int
): Condition(attr, value) {
    override fun opposite(): Condition = LessThanOrEqualsCondition(attr, value)
    override fun test(value: Int): Boolean = value > this.value
    override fun toString(): String {
        return "GreaterThanCondition(attr=$attr, value=$value)"
    }
}

class GreaterThanOrEqualsCondition(
    attr: Char,
    value: Int
): Condition(attr, value) {
    override fun opposite(): Condition = LessThanCondition(attr, value)
    override fun test(value: Int): Boolean = value >= this.value
    override fun toString(): String {
        return "GreaterThanOrEqualsCondition(attr=$attr, value=$value)"
    }
}

class LessThanCondition(
    attr: Char,
    value: Int
): Condition(attr, value) {
    override fun opposite(): Condition = GreaterThanOrEqualsCondition(attr, value)
    override fun test(value: Int): Boolean = value < this.value
    override fun toString(): String {
        return "LessThanCondition(attr=$attr, value=$value)"
    }
}

class LessThanOrEqualsCondition(
    attr: Char,
    value: Int
): Condition(attr, value) {
    override fun opposite(): Condition = GreaterThanCondition(attr, value)
    override fun test(value: Int): Boolean = value <= this.value
    override fun toString(): String {
        return "LessThanOrEqualsCondition(attr=$attr, value=$value)"
    }
}
