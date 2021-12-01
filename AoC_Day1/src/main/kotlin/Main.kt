import java.io.FileReader

fun main(args: Array<String>) {
    val reader = FileReader("scans.txt")
    var prev = -1
    var increases = 0
    val inputs = reader.readLines().map { it.toInt() }
    for (index in 0..inputs.size - 3) {
        val first = inputs[index]
        val second = inputs[index + 1]
        val third = inputs[index + 2]
        val sum = first + second + third
        if (prev != -1 && sum > prev) {
            increases++
        }
        prev = sum
    }
    println("Increases: $increases")
}