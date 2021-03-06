package day4

import java.io.File
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val lines = loadFile("input.txt")
    val numbersDrawn = lines[0]
    val boards = lines.drop(1)
        .chunked(5)
        .map { board ->
            board.map { line -> line.map { it to false }.toMutableList() }.toMutableList()
        }

    traverseBoards(numbersDrawn, boards)
}

private fun traverseBoards(
    numbersDrawn: MutableList<Int>,
    boards: List<MutableList<MutableList<Pair<Int, Boolean>>>>
) {
    var lastWinningBoard = boards[0]
    var lastWinningNumber = -1
    var mutableBoards = boards
    numbersDrawn.forEach { numberDrawn ->
        mutableBoards = mutableBoards.filter { board ->
            updateBoard(numberDrawn, board)
            val didWin = checkBoard(board)
            if (didWin) {
                lastWinningBoard = board
                lastWinningNumber = numberDrawn
            }
            !didWin
        }
    }

    println("Last board won at $lastWinningNumber")
    lastWinningBoard.forEach { line ->
        println(line)
    }
    val sum = sumOfUnmarked(lastWinningBoard)
    println("Result: $sum x $lastWinningNumber = ${sum * lastWinningNumber}")
}

fun sumOfUnmarked(board: MutableList<MutableList<Pair<Int, Boolean>>>): Int {
    return board.flatten().fold(0 to false) { acc, pair ->
        if (!pair.second) {
            acc.copy(acc.first + pair.first)
        } else acc
    }.first
}

fun checkBoard(board: MutableList<MutableList<Pair<Int, Boolean>>>): Boolean {
    board.forEach { line ->
        val win = line.reduce { acc, next ->
            acc.copy(second = acc.second && next.second)
        }.second
        if (win) return true
    }

    for (column in board[0].indices) {
        val win = board.indices
            .map { line -> board[line][column].second }
            .reduce { acc, marked -> acc && marked }
        if (win) return true
    }

    return false
}

fun updateBoard(numberDrawn: Int, board: MutableList<MutableList<Pair<Int, Boolean>>>) {
    for (line in board.indices) {
        for (column in board[line].indices) {
            if (board[line][column].first == numberDrawn) {
                board[line][column] = numberDrawn to true
            }
        }
    }
}

fun loadFile(input: String): MutableList<MutableList<Int>> {
    val regex = Pattern.compile(" +|,")
    return File(input)
        .useLines { sequence ->
            sequence.filter { line -> line.isNotBlank() }
                .map { line ->
                    line.split(regex)
                        .map { entry -> entry.trim() }
                        .filter { it.isNotBlank() }
                        .map { it.toInt() }
                        .toMutableList()
                }
                .toMutableList()
        }
}