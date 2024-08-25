package org.example

interface IFizzBuzz {
    fun printFizzBuzz(start: Int, end: Int)
}

class FizzBuzz : IFizzBuzz {
    override fun printFizzBuzz(start: Int, end: Int) {
        if (start > end || start < 1 || end > 100) {
            throw IllegalArgumentException("El rango debe ser válido y estar entre 1 y 100.")
        }

        for (i in start..end) {
            print(evaluateNumber(i))
            if (i % 10 == 0 || i == end) println() else print("\t")
        }
    }

    private fun evaluateNumber(number: Int): String {
        return when {
            number % 15 == 0 -> "FizzBuzz"
            number % 3 == 0 -> "Fizz"
            number % 5 == 0 -> "Buzz"
            else -> number.toString()
        }
    }
}

fun main() {
    try {
        val fizzBuzz = FizzBuzz()
        fizzBuzz.printFizzBuzz(1, 100)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}