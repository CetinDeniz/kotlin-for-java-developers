package Week2.AssignmentMastermindGame

import kotlin.math.min

/**
 * You can skip this task for now.
 * We'll return to it at the end of the next module
 * after discussing functions for working with collections in a functional style.
 * Complete the following implementation of 'evaluateGuess()' function.
 * Then compare your solution with the solution written in a functional style.
 */

data class Evaluations(val rightPosition: Int, val wrongPosition: Int)

fun evaluateTheGuess(secret: String, guess: String): Evaluation {

    val rightPositions = secret.zip(guess).count { it.first == it.second }

    val commonLetters = "ABCDEF".sumOf { ch ->
        min(secret.count { it == ch }, guess.count { it == ch })
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

fun main(args: Array<String>) {
    val result = Evaluations(rightPosition = 1, wrongPosition = 1)
    evaluateTheGuess("BCDF", "ACEB") // eq result
    evaluateTheGuess("AAAF", "ABCA") // eq result
    evaluateTheGuess("ABCA", "AAAF") // eq result
}