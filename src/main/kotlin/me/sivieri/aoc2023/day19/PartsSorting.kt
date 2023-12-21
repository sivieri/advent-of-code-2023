package me.sivieri.aoc2023.day19

class PartsSorting(input: String) {

    private val workflows: Map<String, Workflow>
    private val parts: List<MachinePart>

    init {
        val (workflows, parts) = input.split("\n\n")
        this.workflows = workflows.split("\n").map { Workflow.parse(it) }.associateBy { it.name }
        this.parts = parts.split("\n").filter { it.isNotBlank() }.map { MachinePart.parse(it) }
    }

    fun sumRatingsOfAccepted(): Int = parts
        .filter { part ->
            var destination = workflows[BEGINNING_LABEL]!!.findDestination(part)
            while (destination !in listOf(ACCEPTED, REJECTED)) {
                destination = workflows[destination]!!.findDestination(part)
            }
            destination == ACCEPTED
        }
        .sumOf { it.rating() }

    fun findTotalCombinations(): Long {
        val data = workflows
            .values
            .filter { it.rules.any { it.destination == ACCEPTED } }
            .flatMap { w ->

                emptyList<List<Rule>>()
            }
        TODO()
    }

    companion object {
        const val BEGINNING_LABEL = "in"
        private const val ACCEPTED = "A"
        private const val REJECTED = "R"
        private const val MIN_ATTR = 1
        private const val MAX_ATTR = 4000
    }

}