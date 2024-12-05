package days

import utils.InputReader

fun isValidInstruction(instruction: String): Boolean {
    if (!instruction.startsWith("(")) return false
    if (!instruction.contains(")")) return false
    val regex = "^[0-9]+(,[0-9]+)?$".toRegex()
    return regex.matches(instruction.substring(instruction.indexOf("(") + 1, instruction.indexOf(")")))
}

fun splitInstructionsByOperation(instructions: String, operation: String): List<String> {
    return instructions.split(operation)
}

fun multiply(instruction: String): Int {
    val startIndex = instruction.indexOf("(")
    val endIndex = instruction.indexOf(")")
    val (firstFactor, secondFactor) = instruction.substring(startIndex + 1, endIndex).split(",")
    return firstFactor.toInt() * secondFactor.toInt()
}

fun partOne(input: String) : Int {
    val instructions = splitInstructionsByOperation(input, "mul")
    var sumOfProducts = 0
    for (instruction in instructions) {
        if (isValidInstruction(instruction)) {
            sumOfProducts += multiply(instruction)
        }
    }
    return sumOfProducts
}

fun partTwo(input: String): Int {
    val instructionRegex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)")
    var mulEnabled = true
    var sumOfProducts = 0

    val matches = instructionRegex.findAll(input)
    for (match in matches) {
        val matchedText = match.value

        if (matchedText == "do()") {
            mulEnabled = true
        } else if (matchedText == "don't()") {
            mulEnabled = false
        } else if (matchedText.startsWith("mul(") && mulEnabled) {
            val (leftFactor, rightFactor) = matchedText.removePrefix("mul(").removeSuffix(")").split(",")
            sumOfProducts += leftFactor.toInt() * rightFactor.toInt()
        }
    }
    return sumOfProducts
}


fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInputAsString("day3.txt")
    val partOneResult = partOne(input)
    val partTwoResult = partTwo(input)
    println("Part 1: $partOneResult")
    println("Part 2: $partTwoResult")
}