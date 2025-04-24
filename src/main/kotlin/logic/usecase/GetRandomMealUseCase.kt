package logic.usecase

import logic.MealRepository
import logic.model.Meal

class GetRandomMealUseCase(private val mealRepository: MealRepository) {
    fun getRandomMeal(): Meal? {
        return  mealRepository.getAllMeals().filter {
            it.mealName != null && it.minutes != null
        }.takeIf { it.isNotEmpty() }?.random()

    }

}