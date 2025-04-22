package logic.usecase

import logic.MealRepository
import logic.model.Meal

class GetRandomMealUseCase(private val mealRepository: MealRepository) {
    fun getRandomMeal(): Meal? {
        val filteredMeals = mealRepository.getAllMeals().filter {
            it.mealName != null && it.minutes != null
        }
        return if (filteredMeals.isNotEmpty()) filteredMeals.random() else null
    }

}