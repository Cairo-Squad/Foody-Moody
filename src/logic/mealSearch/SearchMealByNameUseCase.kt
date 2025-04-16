package logic.mealSearch

import logic.MealRepository
import model.Meal

class SearchMealByNameUseCase(
    private val mealsRepository: MealRepository
) {

    fun searchMealByName(mealName: String, ignoreCase: Boolean): List<Meal> {
        println("Searching for $mealName ......")
        return MealSearchUtil.searchMeals(mealsRepository.getAllMeals(), mealName, ignoreCase)
    }


}


