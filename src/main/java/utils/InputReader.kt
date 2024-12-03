package utils

import java.io.File

class InputReader {
    /**
     * Reads lines from the given input txt file.
     */
    fun readInput(day: String) = File("src/main/resources", "$day").readLines()
}