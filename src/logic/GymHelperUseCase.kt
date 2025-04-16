package logic

import data.MealRepositoryImpl
import model.Meal

class GymHelperUseCase(private val repositoryImpl: MealRepositoryImpl) {

    fun getMealsBasedOnCaloriesAndProtein(calories: Float, protein: Float): List<Meal> {
        val allMeals = repositoryImpl.getAllMeals()

        return allMeals.filter { meal ->
            validateMealMatching(meal, calories, protein)
        }
    }


    private fun validateMealMatching(meal: Meal, calories: Float, protein: Float) =
        meal.nutrition?.calories != null && meal.nutrition.protein != null &&
                meal.nutrition.calories in (calories.minus(50)..(calories.plus(50))) &&
                meal.nutrition.protein in (protein.minus(5)..(protein.plus(5)))

}