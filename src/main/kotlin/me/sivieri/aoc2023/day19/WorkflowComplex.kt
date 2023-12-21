package me.sivieri.aoc2023.day19

import me.sivieri.aoc2023.common.zipWithIndex

data class WorkflowComplex(
    val name: String,
    val rules: List<Pair<String, List<Rule>>>
) {
    companion object {
        fun fromWorkflow(workflow: Workflow): WorkflowComplex {
            val rulesIndexed = workflow.rules
                .zipWithIndex { it }
            val d = rulesIndexed
                .map { (index, r) ->
                    Pair(
                        r.destination,
                        rulesIndexed
                            .filter { it.first < index }
                            .map { it.second.opposite() }
                            .plus(r)
                    )
                }
            return WorkflowComplex(workflow.name, d)
        }
    }
}
