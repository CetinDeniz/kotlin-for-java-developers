package Week2
/**
 * Checking identifier *
 *
 * Implement the function that checks whether a string is a valid identifier.
 * A valid identifier is a non-empty string that starts with a letter or underscore
 * and consists of only letters, digits and underscores.
 */

fun isValidIdentifier(s: String): Boolean {
    val letterRange = 'a'..'z'
    val capitalLetterRange = 'A'..'Z'
    val digitRange = '0'..'9'
    val underScore = '_'

    if (s.isEmpty() || s[0] in digitRange) return false

    val subString = s.substring(1)
    for (char in subString) {
        if (char !in letterRange && char !in capitalLetterRange && char !in digitRange && char != underScore) {
            return false
        }
    }
    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}