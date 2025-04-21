package logic.usecase

import logic.MealRepository
import logic.model.Meal
import logic.model.MealOfRankAndProtein

class GetSeafoodMealsSortedByProteinUseCase(
    private val mealRepository : MealRepository
) {
    companion object {
        const val SEAFOOD = "seafood"
    }

    fun getSeafoodMealsSortedByProtein() : List<MealOfRankAndProtein> {
        return mealRepository.getAllMeals()
            .asSequence()
            .filter { hasSeafoodTag(it) }
            .sortedByDescending { it.nutrition?.protein?.toDouble() ?: 0.0 }
            .mapIndexed { index, meal ->
                val protein = meal.nutrition?.protein?.toDouble() ?: 0.0
                val rank = index + 1
                val name = meal.mealName
                MealOfRankAndProtein(rank, name, protein)
            }
            .toList()
    }

    private fun hasSeafoodTag(meal : Meal) : Boolean {
        val tags = meal.tags?.map { it.lowercase() } ?: emptyList()
        return tags.any { tag -> tag.contains(SEAFOOD) }
    }
}
