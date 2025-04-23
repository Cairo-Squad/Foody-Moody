package logic.usecase

import logic.LogicConstants
import logic.MealRepository
import logic.model.Meal

class GetHighCalorieMealsUseCase(
    private val mealRepository: MealRepository
) {
    fun getHighCalorieMeals(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal -> validHighCalorieMeal(meal) }
            .shuffled()
    }

    private fun validHighCalorieMeal(meal: Meal): Boolean {
        val calories = meal.nutrition?.calories
        return meal.mealName != null
                && calories != null
                && calories > LogicConstants.MINIMUM_CALORIES

    }
}
