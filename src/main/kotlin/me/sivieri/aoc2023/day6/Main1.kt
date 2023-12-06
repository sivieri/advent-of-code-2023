package me.sivieri.aoc2023.day6

import me.sivieri.aoc2023.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(6)
        val racer = BoatRacer(data)
        val wins = racer.countWaysToWin()
        println(wins)
    }

}