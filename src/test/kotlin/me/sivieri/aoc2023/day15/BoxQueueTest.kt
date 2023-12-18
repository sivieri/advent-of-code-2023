package me.sivieri.aoc2023.day15

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import kotlin.test.fail

class BoxQueueTest {

    @Test
    fun `adding to empty`() {
        val queue = BoxQueue<Int>()
        queue.add(1)
        assertThat(queue.contains(1), `is`(true))
    }

    @Test
    fun `adding to queue`() {
        val queue = BoxQueue<Int>()
        queue.add(1)
        queue.add(2)
        assertThat(queue.contains(2), `is`(true))
    }

    @Test
    fun `replacing to empty`() {
        val queue = BoxQueue<Int>()
        queue.replace(1)
        assertThat(queue.size, `is`(0))
    }

    @Test
    fun `replacing to queue`() {
        val queue = BoxQueue<HashmapStep>()
        queue.add(AddStep("one", 1))
        queue.replace(AddStep("one", 2))
        val list = queue.toList()
        assertThat((list.first() as AddStep).lens, `is`(2))
    }

    @Test
    fun `remove from empty`() {
        val queue = BoxQueue<Int>()
        queue.remove(1)
        assertThat(queue.size, `is`(0))
    }

    @Test
    fun `remove from queue`() {
        val queue = BoxQueue<Int>()
        queue.add(1)
        queue.add(2)
        queue.remove(1)
        assertThat(queue.size, `is`(1))
    }

    @Test
    fun `contains in empty`() {
        val queue = BoxQueue<Int>()
        assertThat(queue.contains(1), `is`(false))
    }

    @Test
    fun `contains in queue`() {
        val queue = BoxQueue<Int>()
        queue.add(1)
        queue.add(2)
        assertThat(queue.contains(1), `is`(true))
    }

    @Test
    fun `get all elements from empty`() {
        val queue = BoxQueue<Int>()
        assertThat(queue.toList().size, `is`(0))
    }

    @Test
    fun `get all elements from queue`() {
        val queue = BoxQueue<Int>()
        queue.add(1)
        queue.add(2)
        queue.add(3)
        assertThat(queue.toList(), `is`(listOf(1, 2, 3)))
    }

}