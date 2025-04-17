package logic

import model.Meal

class RandomPotatoMealsUseCase(
    private val mealRepository: MealRepository
) {

    fun get10RandomPotatoMeals(): List<Meal> {
        return mealRepository.getAllMeals()
            .filter(::containsPotatoes)
            .shuffled()
            .take(10)
    }

    private fun containsPotatoes(meal: Meal): Boolean {
        return meal.ingredients != null && meal.ingredients.contains(LogicConstants.POTATOES_INGREDIENT)
    }
}