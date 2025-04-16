package logic

import model.Meal

interface MealRepository {
    fun getAllMeals():List<Meal>
    fun getTwentyRandomMealByCountry(countryName: String):List<Meal>
}