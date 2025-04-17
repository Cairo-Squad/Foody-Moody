package logic

import data.MealRepositoryImpl
import model.Meal

class SuggestMealsToGym(private val mealRepository: MealRepositoryImpl) {

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
                meal.nutrition.calories in (calories.minus(50)..(calories.plus(50))) &&
                meal.nutrition.protein in (protein.minus(5)..(protein.plus(5)))
    }

}