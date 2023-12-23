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
        val paths = workflows
            .values
            .flatMap { it.findPaths() }
            .filter { it.label != REJECTED }
            .groupBy { it.label }
        var accepted = paths[ACCEPTED]!!
        while (!accepted.all { it.component == BEGINNING_LABEL }) {
            accepted = accepted.flatMap { path ->
                if (path.component != BEGINNING_LABEL) path.addComponentRules(paths[path.component]!!)
                else listOf(path)
            }
        }
        val data = accepted
            .map {
                ConditionsResult(
                    it.findValidValues('x'),
                    it.findValidValues('m'),
                    it.findValidValues('a'),
                    it.findValidValues('s')
                )
            }
        return data.map { it.rating() }.sum()
    }

    companion object {
        const val BEGINNING_LABEL = "in"
        private const val ACCEPTED = "A"
        private const val REJECTED = "R"
    }

}