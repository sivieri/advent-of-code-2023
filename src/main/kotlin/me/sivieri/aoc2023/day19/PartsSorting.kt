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
            var destination = workflows[Workflow.BEGINNING_LABEL]!!.findDestination(part)
            while (destination !in listOf(ACCEPTED, REJECTED)) {
                destination = workflows[destination]!!.findDestination(part)
            }
            destination == ACCEPTED
        }
        .sumOf { it.rating() }

    companion object {
        private const val ACCEPTED = "A"
        private const val REJECTED = "R"
    }

}