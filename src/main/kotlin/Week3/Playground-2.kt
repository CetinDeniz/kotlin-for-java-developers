package Week3

/**
 * Safe casts *
 *
 * Type cast 'as' throws ClassCastException, if the cast is unsuccessful.
 * Safe cast 'as?' returns null, if the cast is unsuccessful.
 * Declare the s variable to make the first line print null and the second one throw an exception.
 */
fun main(args: Array<String>) {
    val s = "Value"
    println(s as? Int)    // If value is not Int then return null
    println(s as Int?)    // If value is not Nullable Int then throw exception
}