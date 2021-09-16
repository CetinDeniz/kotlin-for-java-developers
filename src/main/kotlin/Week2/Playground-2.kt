package Week2

/**
 * Sum as an extension function *
 *
 * Change the 'sum' function so that it was declared as an extension to List<Int>.
 */

// fun sum(list: List<Int>): Int {
//     var result = 0
//     for (i in list) {
//         result += i
//     }
//     return result
// }

fun List<Int>.sum(): Int {
    var result = 0
    for (i: Int in this) {
        result += i
    }
    return result
}