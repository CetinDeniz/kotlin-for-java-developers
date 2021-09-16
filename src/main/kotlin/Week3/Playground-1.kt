package Week3

/**
 * isEmptyOrNull() *
 *
 * Add and implement an extension function 'isEmptyOrNull()' on the type String?.
 * It should return true, if the string is null or empty.
 */
fun String?.isEmptyOrNull() = this == null || isEmpty()