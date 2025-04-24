package logic.usecase

import logic.LogicConstants.FOR_LARGE_GROUP
import logic.model.Meal

fun listOfMeals(meal: Meal = Meal(mealName = "italian pasta", tags = null)) = listOf(
    Meal(
        mealName = "egyptian fool",
        tags = listOf(FOR_LARGE_GROUP),
    ),
    Meal(
        mealName = "american pizza",
        tags = listOf(FOR_LARGE_GROUP),
    ),
    Meal(
        mealName = "italian pasta",
        tags = listOf("for-single"),
    ),
    meal
)