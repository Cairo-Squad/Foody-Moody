package logic

import model.Meal
class GetRandomMealUseCase(private val mealRepositry: MealRepository)
{
    fun getRandomMeal(): Meal
    {
        return mealRepositry.getAllMeals().filter {
            it.mealName != null && it.minutes != null
        }.random()
    }
}