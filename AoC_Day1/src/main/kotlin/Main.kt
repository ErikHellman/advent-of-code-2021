import java.io.FileReader

fun main(args: Array<String>) {
    val reader = FileReader("scans.txt")
    var prev = -1
    var increases = 0
    reader.readLines()
        .map { it.toInt() }
        .forEach {
            prev = if (prev != -1) {
                if (it > prev) {
                    increases++
                }
                it
            } else {
                it
            }
        }
    println("Increases: $increases")
}