package logic.usecases

import logic.MealRepository
import model.Meal
import model.Nutrition

class KetoMealUseCase (private val mealRepository: MealRepository){
    private val suggestedIds = mutableSetOf<Int>()

    operator fun invoke(): Meal? {
        return mealRepository.getAllMeals()
            .filter { isKetoFriendly(it.nutrition) }
            .randomOrNull()
            ?.also { it.mealId?.let { it1 -> suggestedIds.add(it1) } }
    }

    private fun isKetoFriendly(nutrition: Nutrition?): Boolean {
        return nutrition?.let {
            it.carbohydrates!! < 10 &&
                    it.protein!! >= 15 &&
                    it.totalFat!! >= 12
        } ?: false
    }
}