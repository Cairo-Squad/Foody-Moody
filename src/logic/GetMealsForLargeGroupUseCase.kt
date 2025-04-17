package logic

import model.Meal

class GetMealsForLargeGroupUseCase(
    private val mealRepository: MealRepository,
) {
    fun getAllMealsForLargeGroup(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal -> isItalianMeal(meal) && isMealForLargeGroup(meal) }
    }

    private fun isItalianMeal(meal: Meal): Boolean {
        return meal.mealName?.contains("Italian", ignoreCase = true) == true
    }

    private fun isMealForLargeGroup(meal: Meal): Boolean {
        return meal.tags?.contains("for-large-groups") == true
    }
}