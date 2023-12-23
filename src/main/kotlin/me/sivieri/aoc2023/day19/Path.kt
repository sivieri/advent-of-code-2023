package me.sivieri.aoc2023.day19

data class Path(
    val label: String,
    val conditions: List<Condition>,
    val component: String
) {
    fun addComponentRules(others: List<Path>): List<Path> = others.map { other ->
        Path(label, conditions + other.conditions, other.component)
    }

    fun findValidValues(attr: Char): List<Int> {
        val conditions = conditions.filter { it.attr == attr }
        val range = (MIN_ATTR..MAX_ATTR).filter { i -> conditions.all { it.test(i) } }
        return range
    }

    companion object {
        private const val MIN_ATTR = 1
        private const val MAX_ATTR = 4000
    }
}
