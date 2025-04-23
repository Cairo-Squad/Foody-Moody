package logic.usecase

import data.errors.NoSuchElementException
import logic.LogicConstants
import logic.MealRepository
import logic.model.Meal

class GetRandomEasyFoodMealsUseCase(
    private val mealRepository: MealRepository
) {
    fun getRandomEasyFoodMeals(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal -> isHighQualityMeal(meal) && isEasyFoodMeal(meal) }
            .takeIf { it.isNotEmpty() }
            ?.shuffled()
            ?.take(10)
            ?: throw NoSuchElementException("No easy food meal found")
    }

    private fun isEasyFoodMeal(meal: Meal): Boolean {
        return meal.minutes!! <= LogicConstants.EASY_FOOD_MINIMUM_MINUTES
                && meal.numberOfIngredients!! <= LogicConstants.EASY_FOOD_MINIMUM_INGREDIENTS
                && meal.numberOfSteps!! <= LogicConstants.EASY_FOOD_MINIMUM_STEPS
    }

    private fun isHighQualityMeal(meal: Meal): Boolean {
        return meal.mealName != null
                && meal.minutes != null
                && meal.numberOfIngredients != null
                && meal.numberOfSteps != null
    }
}