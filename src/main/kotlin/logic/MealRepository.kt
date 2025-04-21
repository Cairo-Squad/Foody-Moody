package logic

import logic.model.Meal

interface MealRepository {
    fun getAllMeals(): List<Meal>
}