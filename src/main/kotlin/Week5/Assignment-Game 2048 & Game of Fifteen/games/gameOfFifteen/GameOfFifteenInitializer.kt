package Week5.`Assignment-Game 2048 & Game of Fifteen`.games.gameOfFifteen

import kotlin.random.Random

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialize the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    /* TO-DO */
    override val initialPermutation by lazy {

        var list = (1..15).toList().shuffled().toMutableList()

        do {
            list = list.swapRandomElements()
        } while (isEven(list))

        list
    }

    private fun MutableList<Int>.swapRandomElements(): MutableList<Int> {

        val firstIndex = Random.nextInt(0, size / 2)
        val secondIndex = Random.nextInt(size / 2, size)

        val tempValue = this[firstIndex]
        this[firstIndex] = this[secondIndex]
        this[secondIndex] = tempValue

        return this
    }

}

