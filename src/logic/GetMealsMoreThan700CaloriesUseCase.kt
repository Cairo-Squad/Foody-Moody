package logic

import model.Meal
import model.Nutrition

class GetMealsMoreThan700CaloriesUseCase(
    private val mealRepository: MealRepository
) {
    fun getMealMoreThan700Calories(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter(::onlyInvalidMeals)
            .filter { it.nutrition!!.calories!! > 700 }
            .shuffled()
    }

    private fun onlyInvalidMeals(meal: Meal): Boolean {
        return meal.mealName != null
                && meal.mealId != null
                && meal.contributorId != null
                && meal.mealDescription != null
                && meal.minutes != null
                && meal.submitted != null
                && meal.tags != null
                && meal.numberOfSteps != null
                && meal.steps != null
                && meal.ingredients != null
                && meal.numberOfIngredients != null
                && meal.nutrition != null
                && onlyInvalidNutrition(meal.nutrition)
    }

    private fun onlyInvalidNutrition(nutrition: Nutrition): Boolean{
        return nutrition.calories != null
                && nutrition.totalFat != null
                && nutrition.sugar != null
                && nutrition.sodium != null
                && nutrition.protein != null
                && nutrition.saturatedFat != null
                && nutrition.carbohydrates != null
    }
}