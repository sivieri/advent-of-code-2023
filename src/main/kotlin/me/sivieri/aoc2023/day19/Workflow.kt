package me.sivieri.aoc2023.day19

data class Workflow(
    val name: String,
    val rules: List<Rule>
) {
    fun findDestination(part: MachinePart): String = rules.first { it.evaluate(part) }.destination

    companion object {
        const val BEGINNING_LABEL = "in"

        fun parse(s: String): Workflow {
            val (name, rules) = s.substring(0, s.length - 1).split("{")
            return Workflow(
                name,
                rules.split(",").map { Rule.parse(it) }
            )
        }
    }
}
