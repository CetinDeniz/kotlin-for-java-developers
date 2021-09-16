package Week5.`Assignment-Game 2048 & Game of Fifteen`.games.game

import Week5.`Assignment-Game 2048 & Game of Fifteen`.board.Direction

interface Game {
    fun initialize()
    fun canMove(): Boolean
    fun hasWon(): Boolean
    fun processMove(direction: Direction)
    operator fun get(i: Int, j: Int): Int?
}
