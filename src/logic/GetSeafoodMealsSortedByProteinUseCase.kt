package logic

import model.Meal

class GetSeafoodMealsSortedByProteinUseCase(
    private val mealRepository: MealRepository
) {
    fun getSeafoodMealsSortedByProtein(): List<String> {
        val allMeals = mealRepository.getAllMeals()
        val seafoodInTags = allMeals.filter { hasSeafoodTag(it) }
        println(seafoodInTags.size)

        val sortedMeals = seafoodInTags.sortedByDescending {
            it.nutrition!!.protein!!.toDouble()
        }

         return sortedMeals.mapIndexed { index, meal ->
            val protein = meal.nutrition?.protein
            val rank = index + 1
            val mealName = meal.mealName
            "Rank: $rank, Name: $mealName, Protein: $protein"
        }
    }

    private fun hasSeafoodTag(meal: Meal): Boolean {
        val tags = meal.tags?.map { it.lowercase() } ?: emptyList()
        return tags.any { tag -> tag.contains("seafood") }
    }
//        val seafoodInIngredients = allMeals.filter { containsSeafoodKeyword(it) }
//        println(seafoodInIngredients.size)
//
//        val combinedSeafoodMeals = (seafoodInTags + seafoodInIngredients)
//            .distinctBy { it.mealId }
//            .filter { it.nutrition?.protein != null }
//        println(combinedSeafoodMeals.size)

    private fun containsSeafoodKeyword(meal: Meal): Boolean {
        val seafoodKeywords = listOf(
            "fish", "shrimp", "crab", "lobster", "scallops", "clams", "tuna",
            "salmon", "cod", "anchovy", "prawns", "mussels", "oyster"
        )

        val ingredients = meal.ingredients?.toString()?.lowercase() ?: ""
        return seafoodKeywords.any { keyword -> kmp(ingredients, keyword) != -1 }
    }

    private fun kmp(text: String, pattern: String): Int {
        val lps = computeLPSArray(pattern)
        var i = 0
        var j = 0

        while (i < text.length) {
            if (pattern[j] == text[i]) {
                i++
                j++
            }
            if (j == pattern.length) {
                return i - j
            } else if (i < text.length && pattern[j] != text[i]) {
                j = if (j != 0) lps[j - 1] else 0
            }
        }
        return -1
    }

    private fun computeLPSArray(pattern: String): IntArray {
        val lps = IntArray(pattern.length)
        var len = 0
        var i = 1
        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++
                lps[i] = len
                i++
            } else {
                len = if (len != 0) lps[len - 1] else 0
                if (len == 0) {
                    lps[i] = 0
                    i++
                }
            }
        }
        return lps
    }
}
