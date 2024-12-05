package days

import utils.InputReader

object Day5 {
    private fun isUpdateCorrect(update: List<Int>, rules: Map<Int, List<Int>>): Boolean {
        for ((key, dependents) in rules) {
            val keyIndex = update.indexOf(key)
                for (dependent in dependents) {
                    val dependentIndex = update.indexOf(dependent)
                    if (dependentIndex != -1 && dependentIndex < keyIndex) {
                        return false
                }
            }
        }
        return true
    }

    private fun getMiddlePageNumber(update: List<Int>): Int {
        return update[update.size / 2]
    }

    private fun createRuleMap(input: List<String>): Map<Int, List<Int>> {
        val rules = input.subList(0, input.indexOf(""))
        val ruleMap = mutableMapOf<Int, MutableList<Int>>()
        for (rule in rules) {
            val (key, value) = rule.split("|").map { it.toInt() }
            ruleMap.computeIfAbsent(key) { mutableListOf() }.add(value)
        }
        return ruleMap
    }

    private fun createUpdateList(input: List<String>): List<List<Int>> {
        return input.subList(input.indexOf("") + 1, input.size).map { it -> it.split(",").map { it.toInt() } }
    }

    private fun fixUpdateOrdering(update: List<Int>, rules: Map<Int, List<Int>>): List<Int> {
        return update.sortedWith { a, b ->
            when {
                rules[a]?.contains(b) == true -> -1
                rules[b]?.contains(a) == true -> 1
                else -> 0
            }
        }
    }

    fun part1(input: List<String>): Int {
        val rules = createRuleMap(input)
        val updates = createUpdateList(input)
        return updates.sumOf {
            if (isUpdateCorrect(it, rules)) {
                getMiddlePageNumber(it)
            } else {
                0
            }
        }
    }

    fun part2(input: List<String>): Int {
        val rules = createRuleMap(input)
        val updates = createUpdateList(input)
        return updates.sumOf {
            if (!isUpdateCorrect(it, rules)) {
                getMiddlePageNumber(fixUpdateOrdering(it, rules))
            } else {
                0
            }
        }
    }
}

fun main() {
    val inputReader = InputReader()
    val input = inputReader.readInput("day5.txt")
    println("Part 1: ${Day5.part1(input)}")
    println("Part 2: ${Day5.part2(input)}")
}