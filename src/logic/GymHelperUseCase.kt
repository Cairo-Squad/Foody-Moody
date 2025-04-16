package logic

import data.MealRepositoryImpl
import model.Meal

class GymHelperUseCase(private val repositoryImpl: MealRepositoryImpl) {

    fun getMealsBasedOnCaloriesAndProtein(calories: Float, protein: Float): List<Meal> {
        val allMeals = repositoryImpl.getAllMeals()

        return allMeals.filter { meal ->
            meal.nutrition?.let {
                val result = if (it.calories != null && it.protein != null) {
                    (it.calories in (calories.minus(50)..(calories.plus(50))))
                            && (it.protein in (protein.minus(5)..(protein.plus(5))))
                } else false
                result
            } == true

        }
    }
}