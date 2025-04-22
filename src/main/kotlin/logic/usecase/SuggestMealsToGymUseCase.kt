package logic.usecase

import logic.MealRepository
import logic.model.Meal

class SuggestMealsToGymUseCase(private val mealRepository: MealRepository) {

    private val allMeals: List<Meal> by lazy { mealRepository.getAllMeals() }

    fun getMealsBasedOnCaloriesAndProtein(calories: Float, protein: Float): List<Meal> {
        require(calories >= 0) { "Calories must be >= 0" }
        require(protein >= 0) { "Protein must be >= 0" }
        return allMeals.filter { meal ->
            doesMealMatches(meal, calories, protein)
        }
    }


    private fun doesMealMatches(
        meal: Meal,
        calories: Float,
        protein: Float
    ): Boolean {
        return meal.nutrition?.calories != null && meal.nutrition.protein != null
                && meal.nutrition.calories in
                (calories.minus(CALORIES_APPROXIMATION)..(calories.plus(CALORIES_APPROXIMATION)))
                && meal.nutrition.protein in
                (protein.minus(PROTEIN_APPROXIMATION)..(protein.plus(PROTEIN_APPROXIMATION)))
    }

    companion object {
        private const val CALORIES_APPROXIMATION = 50
        private const val PROTEIN_APPROXIMATION = 5

    }
}