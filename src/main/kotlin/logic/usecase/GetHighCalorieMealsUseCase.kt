package logic.usecase

import logic.LogicConstants
import logic.MealRepository
import logic.model.Meal

class GetHighCalorieMealsUseCase(
    private val mealRepository: MealRepository
) {
    fun getHighCalorieMeals(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal ->
                mealWithNameAndCalories(meal) && highCalorieMeal(meal)
            }
            .shuffled()
    }

    private fun mealWithNameAndCalories(meal: Meal): Boolean {
        return meal.mealName != null
                && meal.nutrition != null
                && meal.nutrition.calories != null
    }

    private fun highCalorieMeal(meal: Meal): Boolean {
        return meal.nutrition!!.calories!! > LogicConstants.MINIMUM_CALORIES
    }
}
