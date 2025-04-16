package logic

import model.Meal

class GetMealsForLargeGroupUseCase(
    private val mealRepository: MealRepository,
) {
    fun getAllMealsForLargeGroup(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { meal ->
                meal.mealName?.contains("Italian", ignoreCase = true) == true &&
                        meal.tags?.contains("for-large-groups") == true
            }
    }
}