package Week4

/**
 * NPE during initialization *
 *
 * Complete the declaration of the class A without throwing NullPointerException explicitly
 * so that NPE was thrown during the creation of its subclass B instance.
 */

open class C(open val value: String) {
    init {
        println(value.length)
    }
}

class B(override val value: String) : C(value)

fun main(args: Array<String>) {
    B("a")
}