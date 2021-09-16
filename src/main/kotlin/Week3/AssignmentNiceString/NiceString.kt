package Week3.AssignmentNiceString

fun main() {
    println("baaa".isNice())
}

fun String.isNice(): Boolean {

    val containSubstrings = arrayOf("bu", "ba", "be").any { this.contains(it) }
    val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')
    val hasTreeVowels = this.count { vowels.contains(it) } >= 3
    val containsDoubleLetter = this.zipWithNext().any { it.first == it.second }

    return listOf(containSubstrings, hasTreeVowels, containsDoubleLetter).count { it } >= 2
}