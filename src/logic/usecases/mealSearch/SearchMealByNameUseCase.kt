package logic.usecases.mealSearch

import logic.MealRepository
import model.Meal

class SearchMealByNameUseCase(
    private val mealsRepository: MealRepository
) {

    fun searchMealByName(mealName: String): List<Meal> {
        return MealSearchUtil.searchMeals(mealsRepository.getAllMeals(), mealName)
    }


}


