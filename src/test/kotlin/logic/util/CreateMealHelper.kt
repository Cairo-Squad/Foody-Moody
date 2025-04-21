package logic.util

import logic.model.Meal

fun createMeal(
    name: String?,
    minutes: Int?,
    numberOfIngredient: Int?,
    numberOfSteps: Int?
) = Meal(
    mealName = name,
    minutes = minutes,
    numberOfIngredients = numberOfIngredient,
    numberOfSteps = numberOfSteps
)