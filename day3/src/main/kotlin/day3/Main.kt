package day3

import java.io.FileReader

fun main(args: Array<String>) {
    val bitStrings = FileReader("input.txt").readLines()
    val bitStringLength = bitStrings[0].length

    val ogr = filterBitString(bitStringLength, true, bitStrings).joinToString("").toInt(2)
    println("OGR $ogr")

    val cos = filterBitString(bitStringLength, false, bitStrings).joinToString("").toInt(2)
    println("COS $cos")

    println("LSR ${cos * ogr}")
}

private fun filterBitString(
    bitStringLength: Int,
    mostCommon: Boolean,
    candidates: List<String>
): List<String> {
    var result = candidates
    repeat(bitStringLength) { position ->
        val bitCriteria =
            result.map {
                it[position].digitToInt() }.fold(0) { acc, i ->
                return@fold acc + if (i == 1) 1 else -1
            }
        result = result.filter {
            if (mostCommon) {
                if (bitCriteria >= 0) {
                    it[position] == '1'
                } else {
                    it[position] == '0'
                }
            } else {
                if (bitCriteria < 0) {
                    it[position] == '1'
                } else {
                    it[position] == '0'
                }
            }
        }
        if (result.size == 1) return result
    }
    return result
}