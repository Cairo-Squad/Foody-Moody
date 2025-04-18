package logic.usecases

import logic.LogicConstants.FOR_LARGE_GROUP
import logic.LogicConstants.ITALIAN
import logic.MealRepository
import model.Meal

class GetMealsForLargeGroupUseCase(
    private val mealRepository: MealRepository,
) {
    fun getAllMealsForLargeGroup(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal ->
                isItalianMeal(meal) && isMealForLargeGroup(meal)
            }
    }

    private fun isItalianMeal(meal: Meal): Boolean {
        return meal.mealName?.contains(ITALIAN, ignoreCase = true) == true
    }

    private fun isMealForLargeGroup(meal: Meal): Boolean {
        return meal.tags?.any { it.trim() == FOR_LARGE_GROUP } == true
    }
}