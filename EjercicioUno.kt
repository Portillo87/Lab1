package org.example

interface IBaseNumber {
    val value: Int
    fun getType(): NumberType
}

enum class NumberType {
    PRIME, EVEN, ODD
}

data class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun getType() = NumberType.PRIME
}

data class EvenNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun getType() = NumberType.EVEN
}

data class OddNumber(override val value: Int, val divisors: List<Int>) : IBaseNumber {
    override fun getType() = NumberType.ODD
}

class NumberEvaluator {
    fun evaluateRange(n: Int): Map<NumberType, List<IBaseNumber>> {
        if (n <= 0) throw IllegalArgumentException("N debe ser un número positivo mayor que cero.")
        
        return (1..n).map { createNumber(it) }
                     .groupBy { it.getType() }
    }

    private fun createNumber(value: Int): IBaseNumber {
        return when {
            isPrime(value) -> PrimeNumber(value)
            value % 2 == 0 -> EvenNumber(value, findDivisors(value))
            else -> OddNumber(value, findDivisors(value))
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        return (2 until number).none { number % it == 0 }
    }

    private fun findDivisors(number: Int): List<Int> {
        return (1..number).filter { number % it == 0 }
    }
}

fun main() {
    try {
        val evaluator = NumberEvaluator()
        val result = evaluator.evaluateRange(20)
        
        println("Prime numbers count: ${result[NumberType.PRIME]?.size ?: 0}")
        println("Even numbers count: ${result[NumberType.EVEN]?.size ?: 0}")
        println("Odd numbers count: ${result[NumberType.ODD]?.size ?: 0}")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}