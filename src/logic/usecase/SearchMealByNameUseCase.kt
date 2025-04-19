package logic.usecase

import logic.MealRepository
import logic.model.Meal
import logic.utils.MealSearchUtil

class SearchMealByNameUseCase(
    private val mealsRepository: MealRepository
) {

    fun searchMealByName(mealName: String): List<Meal> {
        return MealSearchUtil.searchMeals(mealsRepository.getAllMeals(), mealName)
    }


}


