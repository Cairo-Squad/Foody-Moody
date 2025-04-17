package logic

import model.Meal

class GetRandomEasyFoodMealsUseCase(
    private val mealRepository: MealRepository
) {
    fun getRandomEasyFoodMeals(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter(::isEasyFoodMeal)
            .shuffled()
            .take(10)
    }

    private fun isEasyFoodMeal(meal: Meal): Boolean {
        return meal.mealName != null
                && meal.minutes!! <= LogicConstants.EASY_FOOD_MINIMUM_MINUTES
                && meal.numberOfIngredients!! <= LogicConstants.EASY_FOOD_MINIMUM_INGREDIENTS
                && meal.numberOfSteps!!.toInt() <= LogicConstants.EASY_FOOD_MINIMUM_STEPS
    }
}