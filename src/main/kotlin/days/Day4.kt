package days

object Day4 {
    fun partOne(input: String) : Int {
        val rows = getRows(input)
        val columns = getColumns(input)
        val directions = getDiagonals(input)
        return columns.sumOf { countXmasInString(it) } + rows.sumOf { countXmasInString(it) } + directions.sumOf { countXmasInString(it) }
    }

    fun partTwo(input: String): Int {
        val rows = getRows(input)
        return countXMas(rows)
    }

    private fun getRows(input: String): List<String> {
        return input.split("\n")
    }

    private fun getColumns(input: String) : List<String> {
        val rows = getRows(input)
        val columnCount = rows[0].length
        return (0 until columnCount).map { i -> rows.map { it[i] }.joinToString("") }
    }

    private fun getDiagonals(input: String): List<String> {
        val rows = getRows(input)
        val numRows = rows.size
        val numCols = rows[0].length
        val diagonals = mutableListOf<String>()

        for (d in -(numRows - 1) until numCols) {
            diagonals.add(rows.mapIndexedNotNull { rowIndex, row ->
                val colIndex = rowIndex + d
                if (colIndex in row.indices) row[colIndex] else null
            }.joinToString(""))
        }

        for (d in 0 until numRows + numCols - 1) {
            diagonals.add(rows.mapIndexedNotNull { rowIndex, row ->
                val colIndex = d - rowIndex
                if (colIndex in row.indices) row[colIndex] else null
            }.joinToString(""))
        }

        return diagonals
    }


    private fun countXmasInString(input: String): Int {
        return input.windowed(4).count { it == "XMAS" || it == "SAMX" }
    }

    private fun countXMas(rows: List<String>): Int {
        var count = 0
        val numberOfRows = rows.size
        val numberOfColumns = rows[0].length
        for (i in 0 until numberOfRows) {
            for (j in 0 until numberOfColumns) {
                if (i < numberOfRows - 1 && i > 0 && j < numberOfColumns - 1 && j > 0) {
                    if (rows[i][j] == 'A') {
                        val topLeft = rows[i - 1][j - 1]
                        val topRight = rows[i - 1][j + 1]
                        val bottomLeft = rows[i + 1][j - 1]
                        val bottomRight = rows[i + 1][j + 1]
                        if (isValidMas(topLeft, bottomRight) && isValidMas(topRight, bottomLeft)) {
                            count++
                        }
                }
            }
        }
        }
        return count
    }

    private fun isValidMas(first: Char, second: Char): Boolean {
        return (first == 'M' && second == 'S') || (first == 'S' && second == 'M')
    }
}


fun main() {
    val inputReader = utils.InputReader()
    val input = inputReader.readInputAsString("day4.txt")
    val partOneResult = Day4.partOne(input)
    val partTwoResult = Day4.partTwo(input)
    println("Part 1: $partOneResult")
    println("Part 2: $partTwoResult")
}