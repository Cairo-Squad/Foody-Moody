package logic

import model.Meal
import presentation.meals

class GetMealsForLargeGroupUseCase(
    private val mealRepository: MealRepository
) {
    fun getMealsForGroup(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter{ meal ->
                isItalianMeal(meal) && isItalianMeal(meal)
            }
    }

    private fun isItalianMeal(meal: Meal): Boolean {
        return meal.mealName?.contains("Italian", ignoreCase = true) == true
    }

    private fun isForLargeGroup(meal: Meal): Boolean {
        return meal.tags?.any { it.equals("for-large-groups", ignoreCase = true) == true } == true
    }
}