package logic.usecases

import data.MealRepositoryImpl
import model.Meal

class SuggestMealsToGymUseCase(private val mealRepository: MealRepositoryImpl) {

    fun getMealsBasedOnCaloriesAndProtein(calories: Float, protein: Float): List<Meal> {
        val allMeals = mealRepository.getAllMeals()

        return allMeals.filter { meal ->
            validateMealMatchingToGivenProteinAndCalories(meal, calories, protein)
        }
    }


    private fun validateMealMatchingToGivenProteinAndCalories(
        meal: Meal,
        calories: Float,
        protein: Float
    ): Boolean {
        return meal.nutrition?.calories != null && meal.nutrition.protein != null &&
                meal.nutrition.calories in
                (calories.minus(CALORIES_APPROXIMATION)..(calories.plus(CALORIES_APPROXIMATION))) &&
                meal.nutrition.protein in
                (protein.minus(PROTEIN_APPROXIMATION)..(protein.plus(PROTEIN_APPROXIMATION)))
    }

    companion object {
        private const val CALORIES_APPROXIMATION = 50
        private const val PROTEIN_APPROXIMATION = 5

    }
}