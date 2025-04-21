package logic.usecase

import logic.model.Meal
import logic.model.Nutrition

fun createMealOfSeafood(
    mealName : String? = null ,
    mealId : Int? = null ,
    tags : List<String>? = null ,
    protein : Float? = null ,
) = Meal(
    mealId = mealId ,
    mealName = mealName ,
    mealDescription = null ,
    contributorId = null ,
    minutes = null ,
    submitted = null ,
    tags = tags ,
    nutrition = Nutrition(null , null , null , null , protein , null , null) ,
    numberOfSteps = null ,
    steps = null ,
    ingredients = null ,
    numberOfIngredients = null
)
