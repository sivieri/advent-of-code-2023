package me.sivieri.aoc2023.day19

data class MachinePart(
    val x: Int,
    val m: Int,
    val a: Int,
    val s: Int
) {
    fun rating(): Int = x + m + a + s

    companion object {
        fun parse(s: String): MachinePart =
            s.substring(1, s.length - 1).split(",").let {
                val (x, m, a, s) = it
                MachinePart(
                    x.substring(2).toInt(),
                    m.substring(2).toInt(),
                    a.substring(2).toInt(),
                    s.substring(2).toInt()
                )
            }
    }
}
