package day5

import java.io.File
import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val diagram = HashMap<Point, Int>()
    File("input.txt").readLines().forEach { line ->
        val (start, end) = line
            .split("->")
            .map { pair ->
                val (x, y) = pair.trim()
                    .split(',')
                    .map { num -> num.toInt() }
                Point(x, y)
            }
        println("Got $start $end")

        val yRange = if (start.y <= end.y) start.y .. end.y else start.y downTo end.y
        val xRange = if (start.x <= end.x) start.x..end.x else start.x downTo end.x

        if (start.x == end.x) {
            println("Check vertical")
            for (i in yRange) {
                val point = Point(start.x, i)
                val intersects = (diagram[point] ?: 0) + 1
                diagram[point] = intersects
                println("$point $intersects")
            }
        } else if (start.y == end.y) {
            println("Check horizontal")
            for (i in xRange) {
                val point = Point(i, start.y)
                val intersects = (diagram[point] ?: 0) + 1
                diagram[point] = intersects
                println("$point $intersects")
            }
        } else if ((start.x - end.x).absoluteValue == (start.y - end.y).absoluteValue) {
            println("Check diagonal $start $end")
            xRange.zip(yRange).forEach {
                val point = Point(it.first, it.second)
                val intersects = (diagram[point] ?: 0) + 1
                diagram[point] = intersects
                println("$point $intersects")
            }
        } else {
            println("Skip $start $end")
        }

    }

    println(diagram.filter { it.value >= 2 }.size)
}