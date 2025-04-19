package logic.usecase

import logic.LogicConstants
import logic.MealRepository
import logic.model.Meal

class GetMealsMoreThan700CaloriesUseCase(
    private val mealRepository: MealRepository
) {
    fun getMealMoreThan700Calories(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal ->
                mealWithNameAndCalories(meal) && meal.nutrition!!.calories!! > LogicConstants.MINIMUM_CALORIES
            }
            .shuffled()
    }

    private fun mealWithNameAndCalories(meal: Meal): Boolean {
        return meal.mealName != null
                && meal.nutrition != null
                && meal.nutrition.calories != null
    }
}
