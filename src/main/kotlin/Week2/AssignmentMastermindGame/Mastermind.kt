package Week2.AssignmentMastermindGame

fun main() {
    println(evaluateGuess("FEAE", "EAAE"))
}

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var numberOfRight = 0
    var numberOfWrong = 0
    val secretCharArray = secret.toCharArray()
    val guessCharArray = guess.toCharArray()

    guessCharArray.forEachIndexed { index, char ->
        if (char == secretCharArray[index]) {
            secretCharArray[index] = 'R'
            guessCharArray[index] = 'R'
            numberOfRight++
        }
    }

    guessCharArray.forEachIndexed { index, char ->
        if (char != 'R' && secretCharArray.contains(char)) {
            guessCharArray[index] = 'W'

            val indexSecret = secretCharArray.indexOf(char)
            secretCharArray[indexSecret] = 'W'

            numberOfWrong++
        }
    }

    return Evaluation(numberOfRight, numberOfWrong)
}