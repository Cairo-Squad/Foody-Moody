package logic

import logic.LogicConstant.IRAQ
import logic.LogicConstant.IRAQI
import model.Meal

class GetIraqMeals(private val meals: MealRepository) {
    fun getIraqMeals(): List<Meal> {
        return meals.getAllMeals().filter {
            it.mealDescription?.contains(IRAQ, ignoreCase = true) == true ||
                    it.tags?.any { tag -> tag.equals(IRAQI, ignoreCase = true) } == true
        }
    }
}
