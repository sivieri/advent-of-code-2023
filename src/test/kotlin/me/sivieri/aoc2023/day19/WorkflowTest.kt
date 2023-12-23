package me.sivieri.aoc2023.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class WorkflowTest {

    @Test
    fun parse() {
        val s = "px{a<2006:qkq,m>2090:A,rfg}"
        val expected = Workflow(
            "px",
            listOf(
                LessThanRule('a', 2006, "qkq"),
                GreaterThanRule('m', 2090, "A"),
                DestinationRule("rfg")
            )
        )
        assertThat(Workflow.parse(s), `is`(expected))
    }

    @Test
    fun `single destination`() {
        val w = Workflow(
            "px",
            listOf(
                LessThanRule('a', 2006, "qkq"),
                GreaterThanRule('m', 2090, "A"),
                DestinationRule("rfg")
            )
        )
        val expected = listOf(listOf(
            GreaterThanOrEqualsRule('a', 2006, "qkq"),
            GreaterThanRule('m', 2090, "A")
        ))
        assertThat(w.findRules("A"), `is`(expected))
    }

    @Test
    fun `multiple destination`() {
        val w = Workflow(
            "px",
            listOf(
                LessThanRule('a', 2006, "qkq"),
                GreaterThanRule('m', 2090, "A"),
                DestinationRule("A")
            )
        )
        val expected = listOf(
            listOf(
                GreaterThanOrEqualsRule('a', 2006, "qkq"),
                GreaterThanRule('m', 2090, "A")
            ),
            listOf(
                GreaterThanOrEqualsRule('a', 2006, "qkq"),
                LessThanOrEqualsRule('m', 2090, "A")
            )
        )
        assertThat(w.findRules("A"), `is`(expected))
    }

    @Test
    fun `multiple paths`() {
        val w = Workflow(
            "px",
            listOf(
                LessThanRule('a', 2006, "qkq"),
                GreaterThanRule('m', 2090, "A"),
                DestinationRule("A")
            )
        )
        val expected = listOf(
            Path("qkq", listOf(LessThanCondition('a', 2006)), "px"),
            Path("A", listOf(
                GreaterThanOrEqualsCondition('a', 2006),
                GreaterThanCondition('m', 2090)
            ), "px"),
            Path("A", listOf(
                GreaterThanOrEqualsCondition('a', 2006),
                LessThanOrEqualsCondition('m', 2090)
            ), "px"),
        )
        val paths = w.findPaths()
        assertThat(paths, `is`(expected))
    }


}