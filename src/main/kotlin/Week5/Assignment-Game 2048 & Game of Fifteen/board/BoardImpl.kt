package Week5.`Assignment-Game 2048 & Game of Fifteen`.board

import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = ISquareBoard(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = IGameBoard(width)

open class ISquareBoard(final override val width: Int) : SquareBoard {

    val boardCells = Array(width) { row -> Array(width) { Cell(row + 1, it + 1) } }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i in 1..width && j in 1..width) boardCells[i - 1][j - 1] else null
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException("Wrong inputs : $i,$j")
    }

    override fun getAllCells(): Collection<Cell> {
        return boardCells.flatMap { it.toList() }
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return convertRange(jRange).map { boardCells[i - 1][it - 1] }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return convertRange(iRange).map { boardCells[it - 1][j - 1] }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> if (this.i - 2 >= 0) boardCells[i - 2][this.j - 1] else null
            DOWN -> if (this.i + 1 <= width) boardCells[i][this.j - 1] else null
            LEFT -> if (this.j - 2 >= 0) boardCells[i - 1][this.j - 2] else null
            RIGHT -> if (this.j + 1 <= width) boardCells[i - 1][this.j] else null
        }
    }

    private fun convertRange(jRange: IntProgression): IntProgression {
        val start = if (jRange.first > width) width else jRange.first
        val end = if (jRange.last > width) width else jRange.last

        return if (start > end) start downTo end else start..end
    }

}


class IGameBoard<T>(width: Int) : ISquareBoard(width), GameBoard<T> {

    private val mappedCells: MutableMap<Cell, T?> = boardCells.flatMap { it.toList() }
        .associateWith { null }
        .toMutableMap()

    override fun get(cell: Cell): T? {
        return mappedCells[cell]
    }

    override fun set(cell: Cell, value: T?) {
        mappedCells[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return mappedCells.filterValues { predicate(it) }.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        val filtered = mappedCells.filterValues(predicate).keys
        return if (filtered.isNotEmpty()) filtered.first() else null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return mappedCells.any { predicate(it.value) }
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return mappedCells.all { predicate(it.value) }
    }
}