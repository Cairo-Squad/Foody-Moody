package logic

import data.MealRepositoryImpl
import model.Meal

class GymHelperUseCase(private val repositoryImpl: MealRepositoryImpl) {

    fun getMealsBasedOnCaloriesAndProtein(calories: Float, protein: Float): List<Meal> {
        val allMeals = repositoryImpl.getAllMeals()

        return allMeals.filter { meal ->
            meal.nutrition?.let {
                val result = if (it.calories != null && it.protein != null) {
                    (it.calories in (calories.minus(300)..(calories.plus(300))))
                            && (it.protein in (protein.minus(15)..(protein.plus(15))))
                } else false
                result
            } == true

        }
    }
}