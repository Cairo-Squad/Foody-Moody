package logic.usecases

import logic.MealRepository
import model.Meal

class RandomPotatoMealsUseCase(
    private val mealRepository: MealRepository
) {

    fun get10RandomPotatoMeals(): List<Meal> {
        return getPotatoMeals()
            .shuffled()
            .take(10)
    }

    private fun getPotatoMeals(): List<Meal> {
        return mealRepository.getAllMeals().filter(::containsPotatoes)
    }

    private fun containsPotatoes(meal: Meal): Boolean {
        return meal.ingredients != null && meal.ingredients.contains("potatoes")
    }
}