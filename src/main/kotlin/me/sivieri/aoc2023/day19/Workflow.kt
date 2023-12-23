package me.sivieri.aoc2023.day19

import me.sivieri.aoc2023.common.zipWithIndex
import java.lang.IllegalArgumentException

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

    fun findPaths(): List<Path> {
        val rulesIndexed = rules
            .zipWithIndex { it }
        val prepared = rulesIndexed
            .map { (index, r) ->
                rulesIndexed
                    .filter { it.first < index }
                    .map { it.second.opposite() }
                    .plus(r)
            }
        return prepared.map { rules ->
            val label = rules.last().destination
            val conditions = rules
                .filter { it !is DestinationRule }
                .map { rule ->
                    when (rule) {
                        is LessThanRule -> LessThanCondition(rule.attr, rule.value)
                        is LessThanOrEqualsRule -> LessThanOrEqualsCondition(rule.attr, rule.value)
                        is GreaterThanRule -> GreaterThanCondition(rule.attr, rule.value)
                        is GreaterThanOrEqualsRule -> GreaterThanOrEqualsCondition(rule.attr, rule.value)
                        else -> throw IllegalArgumentException("No destination here")
                    }
                }
            Path(label, conditions, name)
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
