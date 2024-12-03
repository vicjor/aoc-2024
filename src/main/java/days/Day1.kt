package days

import utils.InputReader

fun sumDistances(leftList: List<Int>, rightList: List<Int>): Int {
    val distances = mutableListOf<Int>()
    for (i in leftList.indices) {
        val distance = maxOf(leftList[i] - rightList[i], rightList[i] - leftList[i])
        distances.add(distance)
    }
    return distances.sum()
}

fun sumSimilarities(leftList: List<Int>, rightList: List<Int>) : Int {
    val similarityScores = mutableListOf<Int>()
    for (i in leftList) {
        val similarity = rightList.count{ it == i} * i
        similarityScores.add(similarity)
    }
    return similarityScores.sum()
}

fun part1(leftList: List<Int>, rightList: List<Int>) : Int {
    return sumDistances(leftList, rightList)
}

fun part2(leftList: List<Int>, rightList: List<Int>): Int {
    return sumSimilarities(leftList, rightList)
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("day1.txt")
    val leftList = input.map { it.split("   ").first().toInt() }.sorted()
    val rightList = input.map { it.split("   ").last().toInt() }.sorted()
    val sumOfDistances = part1(leftList, rightList)
    val sumOfSimilarities = part2(leftList, rightList)
    println("Part 1: Sum of distances is $sumOfDistances")
    println("Part 2: Sum of similarities is $sumOfSimilarities")
}