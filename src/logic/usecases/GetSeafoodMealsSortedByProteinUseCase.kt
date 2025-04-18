package logic.usecases

import logic.MealRepository
import model.Meal
import model.ShowMeal

class GetSeafoodMealsSortedByProteinUseCase(
    private val mealRepository: MealRepository
) {
    fun getSeafoodMealsSortedByProtein(): List<ShowMeal> {
        return mealRepository.getAllMeals()
            .asSequence()
            .filter { hasSeafoodTag(it) }
            .sortedByDescending { it.nutrition?.protein?.toDouble() ?: 0.0 }
            .mapIndexed { index, meal ->
                val protein = meal.nutrition?.protein?.toDouble() ?: 0.0
                val rank = index + 1
                val name = meal.mealName
                ShowMeal(rank, name, protein)
            }
            .toList()
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
//
//    private fun containsSeafoodKeyword(meal: Meal): Boolean {
//        val seafoodKeywords = listOf(
//            "fish", "shrimp", "crab", "lobster", "scallops", "clams", "tuna",
//            "salmon", "cod", "anchovy", "prawns", "mussels", "oyster"
//        )
//
//        val ingredients = meal.ingredients?.toString()?.lowercase() ?: ""
//        return seafoodKeywords.any { keyword -> ingredients.contains(keyword) }
//    }

}
