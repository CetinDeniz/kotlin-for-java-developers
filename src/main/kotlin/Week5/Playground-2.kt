package Week5

/**
 * Fibonacci sequence *
 *
 * Implement the function that builds a sequence of Fibonacci numbers using 'sequence' function. Use 'yield'.
 */

/* TO-DO */
fun fibonacci(): Sequence<Int> = sequence {
    var first = 0
    var second = 1
    yield(first)
    yield(second)

    var new: Int
    while (true) {
        new = first + second
        yield(new)
        first = second
        second = new
    }
}

fun main(args: Array<String>) {
    fibonacci().take(4).toList().toString() // eq "[0, 1, 1, 2]"

    fibonacci().take(10).toList().toString() // eq "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
}