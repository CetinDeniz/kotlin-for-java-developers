package Week4.AssignmentRationals

import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {
    init {
        if (denominator == BigInteger.ZERO) throw IllegalArgumentException("Denominator can't be 0")
    }

    private val gcd: BigInteger = numerator.gcd(denominator)

    override fun toString(): String {
        val division = denominator / gcd

        val scPart: String
        if (numerator < BigInteger.ZERO && denominator < BigInteger.ZERO) {
            scPart = if (division == BigInteger.ONE) "" else "/${division.abs()}"
            return "${numerator.abs() / gcd}$scPart"
        } else if (denominator < BigInteger.ZERO) {
            scPart = if (division == BigInteger.ONE) "" else "/${division.abs()}"
            return "${-numerator / gcd}$scPart"
        }
        scPart = if (division == BigInteger.ONE) "" else "/${division}"
        return "${numerator / gcd}$scPart"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rational) return false

        return numerator * other.denominator == denominator * other.numerator
    }

    override fun hashCode(): Int {
        return 31 * numerator.hashCode() + denominator.hashCode()
    }

    override fun compareTo(other: Rational): Int {
//        return (numerator * other.denominator - other.numerator * denominator).signum()
        return (numerator * other.denominator).compareTo(denominator * other.numerator)
    }

}

infix fun <T : Number> T.divBy(denominator: T): Rational {
    return Rational(this.toLong().toBigInteger(), denominator.toLong().toBigInteger())
}

operator fun Rational.plus(other: Rational): Rational {
    return Rational((numerator * other.denominator) + (denominator * other.numerator), denominator * other.denominator)
}

operator fun Rational.minus(other: Rational): Rational {
    return Rational((numerator * other.denominator) - (denominator * other.numerator), denominator * other.denominator)
}

operator fun Rational.times(other: Rational): Rational {
    return Rational(numerator * other.numerator, denominator * other.denominator)
}

operator fun Rational.div(other: Rational): Rational {
    return Rational(numerator * other.denominator, denominator * other.numerator)
}

operator fun Rational.unaryMinus(): Rational = Rational(-numerator, denominator)

fun String.toRational(): Rational {
    fun String.toBigIntegerOrFail() =
        toBigIntegerOrNull()
            ?: throw IllegalArgumentException("Expecting rational in the form of 'n/d' or 'n', was: '${this@toRational}'")

    if (!contains("/")) {
        return Rational(this.toBigIntegerOrFail(), BigInteger.ONE)
    }
    val (numerator, denominator) = split("/")
    return Rational(numerator.toBigIntegerOrFail(), denominator.toBigIntegerOrFail())
}


fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")

    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}