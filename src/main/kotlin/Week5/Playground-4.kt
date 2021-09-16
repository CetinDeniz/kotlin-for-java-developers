package Week5

/**
 * Member extensions *
 *
 * Implement member extension functions 'record' and 'unaryPlus'
 * so that the code below compiled and stored specified words.
 * These functions should be unavailable outside the 'Words' class.
 */

class Words {
    private val list = mutableListOf<String>()

    fun String.record() = list.add(this)   /* TO-DO */
    operator fun String.unaryPlus() = record() /* TO-DO */

    override fun toString() = list.toString()
}

fun main(args: Array<String>) {
    val words = Words()
    with(words) {
        // The following two lines should compile:
        "one".record()
        +"two"
    }
    words.toString() // eq "[one, two]"
}