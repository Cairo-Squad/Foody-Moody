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
        return meal.isHighQualityMeal
                && meal.minutes!! <= 30
                && meal.numberOfIngredients!! <= 5
                && meal.numberOfSteps!! <= 6
    }
}