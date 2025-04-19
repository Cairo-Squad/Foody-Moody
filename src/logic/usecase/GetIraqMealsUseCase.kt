package logic.usecase

import logic.LogicConstants.IRAQ
import logic.LogicConstants.IRAQI
import logic.MealRepository
import logic.model.Meal

class GetIraqMealsUseCase(private val meals: MealRepository) {
    fun getIraqMeals(): List<Meal> {
        return meals.getAllMeals().filter {
            it.mealDescription?.contains(IRAQ, ignoreCase = true) == true ||
                    it.tags?.any { tag -> tag.equals(IRAQI, ignoreCase = true) } == true
        }
    }
}
