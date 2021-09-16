package Week4

import kotlin.random.Random

/**
 * Unstable val *
 *
 * Implement the property 'foo' so that it produced a different value on each access.
 * Note that you can modify the code outside the 'foo' getter (e.g. add additional imports or properties).
 */

val foo: Int
    get() = Random.nextInt(10)  // 0..9  or 0 until 10

fun main(args: Array<String>) {
    // The values should be different:
    println(foo)
    println(foo)
    println(foo)
}