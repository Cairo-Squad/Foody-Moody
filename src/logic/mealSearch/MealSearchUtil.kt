package logic.mealSearch

import model.Meal

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
     *             -> (n^2) ->levenshtein Algorithm.
     *
     * @return A list of `Meal` objects that match the search query. It returns meals that exactly match using KMP
     *         or those that are within a Levenshtein distance of 4 characters from the query if no exact matches are found.
     */
    fun searchMeals(meals: List<Meal>, query: String, ignoreCase: Boolean): List<Meal> {
        val normalizedQuery = if (ignoreCase) query.lowercase().trim() else query.trim()

        // 1. Try fast KMP search
        println("trying to get exact matches.....")
        val exactMatches = meals.filter { meal ->
            kmpSearch(text = meal.mealName?.lowercase() ?: "", query = normalizedQuery)
        }

        if (exactMatches.isNotEmpty()) return exactMatches

        // 2. Fallback to typo-tolerant search   (Levenshtein)
        println("no exact matches found, now trying to get similar matches.....")
        return meals.filter { meal ->
            levenshtein(meal.mealName?.lowercase() ?: "", normalizedQuery) <= TOLERANCE_ALLOWED

        }
    }

    /***
     * KMP - pattern matching algorithm
     * It does not allow for missing, extra, or swapped letters.
     * Worst case complexity O(n)
     */
    // KMP Search
    private fun kmpSearch(text: String, query: String): Boolean {
        if (query.isEmpty()) return true
        val lps = buildLPS(query)
        var i = 0
        var j = 0
        while (i < text.length) {
            if (query[j] == text[i]) {
                i++
                j++
                if (j == query.length) return true
            } else {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++  // advance text pointer when j is 0 and still no match
                }
            }
        }
        return false
    }

    private fun buildLPS(pattern: String): IntArray {
        val lps = IntArray(pattern.length)
        var length = 0
        var i = 1
        while (i < pattern.length) {
            if (pattern[i] == pattern[length]) {
                length++
                lps[i] = length
                i++
            } else {
                if (length != 0) {
                    length = lps[length - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }
        return lps
    }

    // Levenshtein Distance
    private fun levenshtein(text: String, query: String): Int {
        val dp = Array(text.length + 1) { IntArray(query.length + 1) }
        for (i in 0..text.length) dp[i][0] = i
        for (j in 0..query.length) dp[0][j] = j

        for (i in 1..text.length) {
            for (j in 1..query.length) {
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + if (text[i - 1] == query[j - 1]) 0 else 1
                )
            }
        }
        return dp[text.length][query.length]
    }

    private const val TOLERANCE_ALLOWED = 4

}