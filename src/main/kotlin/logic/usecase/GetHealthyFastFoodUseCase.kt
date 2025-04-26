package logic.usecase

import logic.MealRepository
import logic.model.Meal

class GetHealthyFastFoodUseCase(
    private val repo: MealRepository
) {

    fun getHealthyFastFood(): List<Meal> {
        val mealsCanBePreparedIn15Minutes =
            repo.getAllMeals().filter(::isValidMeal)

        require(mealsCanBePreparedIn15Minutes.isNotEmpty()) { "Some data is null" }
        return mealsCanBePreparedIn15Minutes
            .sortedWith(
                compareBy(
                    { it.nutrition?.totalFat },
                    { it.nutrition?.saturatedFat },
                    { it.nutrition?.carbohydrates }
                )
            ).take(NUMBER_OF_REQUIRED_FAST_FOOD_MEALS)

    }

    companion object {
        private const val NUMBER_OF_REQUIRED_FAST_FOOD_MEALS = 5
    }

    private fun isValidMeal(meal: Meal): Boolean {
        return meal.nutrition?.totalFat != null
                && meal.nutrition.carbohydrates != null
                && meal.nutrition.saturatedFat != null
                && meal.minutes != null
                && meal.minutes <= 15
    }
}