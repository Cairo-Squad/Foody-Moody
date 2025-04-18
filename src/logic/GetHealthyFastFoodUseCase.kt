package logic

import model.Meal

class GetHealthyFastFoodUseCase(
    private val repo: MealRepository
) {

    fun getHealthyFastFood(): List<Meal> {
        val mealsCanBePreparedIn15Minutes = repo.getAllMeals().filter {
            it.nutrition?.totalFat != null && it.nutrition.carbohydrates != null
                    && it.nutrition.saturatedFat != null &&
                    it.minutes != null && it.minutes <= 15
        }
        val healthiestMeals = mealsCanBePreparedIn15Minutes.sortedWith(
            compareBy(
                { it.nutrition?.totalFat },
                { it.nutrition?.saturatedFat },
                { it.nutrition?.carbohydrates }
            )
        )
        val topHealthyMeals = healthiestMeals.take(10)
        return topHealthyMeals
    }


}