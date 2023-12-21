package me.sivieri.aoc2023.day19

import me.sivieri.aoc2023.common.zipWithIndex

data class Workflow(
    val name: String,
    val rules: List<Rule>
) {
    fun findDestination(part: MachinePart): String = rules.first { it.evaluate(part) }.destination

    fun findRules(destination: String): List<List<Rule>> {
        val rulesIndexed = rules
            .zipWithIndex { it }
        return rulesIndexed
            .filter { it.second.destination == destination }
            .map { (index, r) ->
                rulesIndexed
                    .filter { it.first < index }
                    .map { it.second.opposite() }
                    .plus(r)
                    .filter { it !is DestinationRule }
            }
    }

    companion object {
        fun parse(s: String): Workflow {
            val (name, rules) = s.substring(0, s.length - 1).split("{")
            return Workflow(
                name,
                rules.split(",").map { Rule.parse(it) }
            )
        }
    }
}
