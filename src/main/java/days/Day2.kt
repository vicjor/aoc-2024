package days

import utils.InputReader
import kotlin.math.abs

fun isReportSafe(report: List<Int>): Boolean {
    if ((isLevelsIncreasing(report) || isLevelsDecreasing(report)) && isAllAdjacentLevelsLegal(report)) {
        return true
    }
    return false
}

fun isLevelsIncreasing(report: List<Int>): Boolean {
    for (i in 0 until report.size - 1) {
        val current = report[i]
        val next = report[i + 1]
        if (current >= next) {
            return false
        }
    }
    return true
}

fun isLevelsDecreasing(report: List<Int>): Boolean {
    for (i in 0 until report.size - 1) {
        val current = report[i]
        val next = report[i + 1]
        if (current <= next) {
            return false
        }
    }
    return true
}

fun isAllAdjacentLevelsLegal(report: List<Int>) : Boolean {
    for (i in 0 until report.size - 1) {
        val current = report[i]
        val next = report[i + 1]
        if (abs(current - next) !in 1..3) {
            return false
        }
    }
    return true
}

fun isReportSafeWithDampener(report: List<Int>): Boolean {
    if (isReportSafe(report)) return true
    for (i in report.indices) {
        val dampenedReport = report.toMutableList().apply { removeAt(i) }
        if (isReportSafe(dampenedReport)) return true
    }
    return false
}

fun part1(reports: List<List<Int>>) : Int {
    return reports.count { isReportSafe(it)}
}

fun part2(reports: List<List<Int>>): Int {
    return reports.count { isReportSafeWithDampener(it) }
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("day2.txt")
    val reports = input.map{ it -> it.split(" ").map {it.toInt()}}
    val part1Result = part1(reports)
    val part2Result = part2(reports)
    println("Part 1: There are $part1Result safe reports")
    println("Part 2: There are $part2Result safe reports when using the report dampener")
}