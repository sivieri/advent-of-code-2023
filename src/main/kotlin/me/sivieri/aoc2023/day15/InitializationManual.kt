package me.sivieri.aoc2023.day15

import me.sivieri.aoc2023.common.zipWithIndex

class InitializationManual(input: String) {

    private val manual = input.trim().split(",").map { i ->
        if (i.contains("-")) RemoveStep(i.substring(0, i.length - 1))
        else {
            val (label, number) = i.split("=")
            AddStep(label, number.toInt())
        }
    }
    private val boxes = (0..255).associateWith { BoxQueue<HashmapStep>() }

    fun sumInstructions(): Long = manual.fold(0L) { acc, it -> acc + HolidayHash.hash(it.toString()) }

    fun fillAndGetBoxes(): Map<Int, BoxQueue<HashmapStep>> {
        fillBoxes()
        return boxes
    }

    fun focusingPower(): Long {
        fillBoxes()
        return boxes
            .entries
            .fold(0L) { acc, (index, queue) ->
                acc + queue.zipWithIndex { it }.sumOf { (i, step) ->
                    (index + 1) * (i + 1) * (step as AddStep).lens
                }
            }
    }

    private fun fillBoxes() {
        manual.forEach { step ->
            when (step) {
                is AddStep -> {
                    val box = boxes[HolidayHash.hash(step.label)]!!
                    if (box.contains(step)) box.replace(step)
                    else box.add(step)
                }
                is RemoveStep -> {
                    boxes[HolidayHash.hash(step.label)]!!.remove(step)
                }
            }
        }
    }

}