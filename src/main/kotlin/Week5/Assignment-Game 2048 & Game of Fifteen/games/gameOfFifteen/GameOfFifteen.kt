package Week5.`Assignment-Game 2048 & Game of Fifteen`.games.gameOfFifteen

import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.Cell
import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.Direction
import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.Direction.*
import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.createGameBoard
import Week5.`Assignment-Game 2048 & Game of Fifteen`.games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

/* All - TO-DO */
class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)
    private val finalVersion: MutableList<Int?> = (1..15).toMutableList()

    init {
        finalVersion.add(null)
    }

    override fun initialize() {
        val values = initializer.initialPermutation
        val allCells = board.getAllCells().toList()
        values.forEachIndexed { i, value -> board[allCells[i]] = value }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean {
        var i = 0
        return board.getAllCells().all { cell ->
            i++
            board[cell] == finalVersion[i - 1]
        }
    }

    override fun processMove(direction: Direction) {
        val nullCell = board.find { it == null } as Cell
        val gonnaMove: Cell? = when (direction) {
            UP -> board.getCellOrNull(nullCell.i + 1, nullCell.j)
            DOWN -> board.getCellOrNull(nullCell.i - 1, nullCell.j)
            LEFT -> board.getCellOrNull(nullCell.i, nullCell.j + 1)
            RIGHT -> board.getCellOrNull(nullCell.i, nullCell.j - 1)
        }
        gonnaMove?.let {
            board[nullCell] = board[gonnaMove]
            board[gonnaMove] = null
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return board[board.getCell(i, j)]
    }
}