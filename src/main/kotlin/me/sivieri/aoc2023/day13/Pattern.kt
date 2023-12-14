package me.sivieri.aoc2023.day13

class Pattern(data: String) {

    private val matrix = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toList().toTypedArray() }
        .toTypedArray()
    private val maxX = matrix[0].size
    private val maxY = matrix.size

    fun columnsAndRowWhenReflection(): Pair<ReflectionResult?, ReflectionResult?> = Pair(columnsLeftOfReflection().firstOrNull(), rowsAboveReflection().firstOrNull())

    fun columnsAndRowWhenReflectionWithChanges(): ReflectionResult {
        val (cols, rows) = Pair(columnsLeftOfReflection(true), rowsAboveReflection(true))
        val (col, row) = Pair(columnsLeftOfReflection().firstOrNull(), rowsAboveReflection().firstOrNull())
        val possibleRow = rows.find { row == null || it.value != row.value }
        val possibleCol = cols.find { col == null || it.value != col.value }
        println("${possibleCol?.value ?: 0} ${possibleRow?.value ?: 0}")
        return possibleRow
            ?: possibleCol
            ?: ReflectionResult(0, 0, ReflectionPosition.NONE, true)
    }

    fun columnsLeftOfReflection(change: Boolean = false): List<ReflectionResult> {
        return (0 until maxX)
            .zipWithNext()
            .map { pair ->
                var changed = false
                var (left, right) = pair
                while (left >= 0 && right < maxX) {
                    val lc = matrix.map { it[left] }
                    val rc = matrix.map { it[right] }
                    if (lc != rc) {
                        val diff = lc.zip(rc).count { it.first != it.second }
                        if (change && !changed && diff == 1){
                            changed = true
                        }
                        else return@map ReflectionResult(0, 0, ReflectionPosition.COLUMN, changed)
                    }
                    left--
                    right++
                }
                val remaining = if (left < 0) right
                else left
                ReflectionResult(pair.first + 1, remaining, ReflectionPosition.COLUMN, changed)
            }
            .filter { it.value != 0 }
    }

    fun rowsAboveReflection(change: Boolean = false): List<ReflectionResult> {
        return (0 until maxY)
            .zipWithNext()
            .map { pair ->
                var changed = false
                var (up, down) = pair
                while (up >= 0 && down < maxY) {
                    val uc = matrix[up].toList()
                    val dc = matrix[down].toList()
                    if (uc != dc) {
                        val diff = uc.zip(dc).count { it.first != it.second }
                        if (change && !changed && diff == 1){
                            changed = true
                        }
                        else return@map ReflectionResult(0, 0, ReflectionPosition.COLUMN, changed)
                    }
                    up--
                    down++
                }
                val remaining = if (up < 0) down
                else up
                ReflectionResult(pair.first + 1, remaining, ReflectionPosition.ROW, changed)
            }
            .filter { it.value != 0 }
    }

    fun matrixRepresentation(): String = matrix.joinToString(", ") { it.joinToString("") }

    companion object {
        private const val ASH = '.'
        private const val ROCK = '#'
    }

}
