package day2

import java.io.FileReader

fun main(args: Array<String>) {
    val reader = FileReader("input.txt")
    var depth = 0
    var horizontal = 0
    var aim = 0
    reader.readLines()
        .forEach {
            val (direction, valueText) = it.split(" ")
            val amount = valueText.toInt()
            when (direction) {
                "forward" -> {
                    horizontal += amount
                    depth += aim * amount
                }
                "up" -> {
                    aim += -amount
                }
                "down" -> {
                    aim += amount
                }
            }
        }
    println(horizontal * depth)

}
