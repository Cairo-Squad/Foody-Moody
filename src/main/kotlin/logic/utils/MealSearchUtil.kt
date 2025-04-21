package logic.utils

import logic.model.Meal

object MealSearchUtil {


    /**
     * Searches for meals based on a given query string.
     * It first attempts to find exact matches using the
     * Knuth-Morris-Pratt (KMP) algorithm.
     * If no exact matches are found, it falls back to a typo-tolerant search using the Levenshtein distance algorithm to allow for small mistakes or variations in the search query.
     *
     *
     * @param meals The list of `Meal` objects to search through.
     * @param query The search query string entered by the user.
     *
     * @Complexity -> O(n) -> KMP Algorithm
     *             -> O(n^2) ->levenshtein Algorithm.
     *
     * @return A list of `Meal` objects that match the search query. It returns meals that exactly match using KMP
     *         or those that are within a Levenshtein distance of 4 characters from the query if no exact matches are found.
     */
    fun searchMeals(meals: List<Meal>, query: String): List<Meal> {
        val normalizedQuery = query.lowercase().trim()

        val exactMatches = meals.filter { meal ->
            kmpSearch(text = meal.mealName ?: "", pattern = normalizedQuery)
        }

        if (exactMatches.isNotEmpty()) return exactMatches

        return meals.filter { meal ->
            val mealWords = (meal.mealName ?: "").lowercase().split(" ")
            mealWords.any { word ->
                levenshtein(word, normalizedQuery) <= TOLERANCE_ALLOWED
            }

        }
    }

    private fun kmpSearch(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return true

        val longestPrefixSuffix = buildLongestPrefixSuffix(pattern)
        var textIndex = 0
        var patternIndex = 0

        while (textIndex < text.length) {
            if (pattern[patternIndex] == text[textIndex]) {
                textIndex++
                patternIndex++
                if (patternIndex == pattern.length) return true
            } else {
                if (patternIndex != 0) {
                    patternIndex = longestPrefixSuffix[patternIndex - 1]
                } else {
                    textIndex++
                }
            }
        }
        return false
    }

    private fun buildLongestPrefixSuffix(pattern: String): IntArray {
        val longestPrefixSuffix = IntArray(pattern.length)
        var previousLPSIndex = 0
        var currentLPSIndex = 1

        while (currentLPSIndex < pattern.length) {
            if (pattern[currentLPSIndex] == pattern[previousLPSIndex]) {
                previousLPSIndex++
                longestPrefixSuffix[currentLPSIndex] = previousLPSIndex
                currentLPSIndex++
            } else {
                if (previousLPSIndex != 0) {
                    previousLPSIndex = longestPrefixSuffix[previousLPSIndex - 1]
                } else {
                    longestPrefixSuffix[currentLPSIndex] = 0
                    currentLPSIndex++
                }
            }
        }

        return longestPrefixSuffix
    }


    private fun levenshtein(source: String, target: String): Int {
        val distanceMatrix = Array(source.length + 1) { IntArray(target.length + 1) }

        for (sourceIndex in 0..source.length) {
            distanceMatrix[sourceIndex][0] = sourceIndex
        }

        for (targetIndex in 0..target.length) {
            distanceMatrix[0][targetIndex] = targetIndex
        }

        for (sourceIndex in 1..source.length) {
            for (targetIndex in 1..target.length) {
                val cost = if (source[sourceIndex - 1] == target[targetIndex - 1]) 0 else 1
                distanceMatrix[sourceIndex][targetIndex] = minOf(
                    distanceMatrix[sourceIndex - 1][targetIndex] + 1,
                    distanceMatrix[sourceIndex][targetIndex - 1] + 1,
                    distanceMatrix[sourceIndex - 1][targetIndex - 1] + cost
                )
            }
        }

        return distanceMatrix[source.length][target.length]
    }


    private const val TOLERANCE_ALLOWED = 4

}