package day3

import java.io.FileReader

fun main(args: Array<String>) {
    val gammaArr = FileReader("input.txt")
        .readLines()
        .map {
            it.split("")
                .filter { c -> c.isNotBlank() }
                .map { n -> n.toInt() }
        }
//        .onEach { println("Input: $it") }
        .flatten()
        .foldIndexed(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) { index, acc, i ->
            val step = if (i == 1) 1 else -1
            val bitPos = index % 12
            acc[bitPos] += step
            acc
        }
        .map { if (it >= 0) 1 else 0 }


    val gammaString = gammaArr.joinToString(separator = "")
    println("Gamma string: $gammaString")
    val gamma = gammaString.toInt(2)
    println("Gamma: $gamma")

    val epsilon = gammaArr.map {
        if (it == 0) 1 else 0
    }.joinToString(separator = "").toInt(2)

    println("Epsilon: $epsilon")
}