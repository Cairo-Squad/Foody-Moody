package model

import java.time.LocalDate


data class Meal(
    val mealName:String?,
    val mealId: Int?,
    val mealDescription: String? = null,
    val contributorId: Int? = null,
    val minutes: Int? = null,
    val submitted: LocalDate? = null,
    val tags: List<String>? = null,
    val nutrition: Nutrition? = null,
    val numberOfSteps: String? = null,
    val steps: List<String>? = null,
    val ingredients: List<String>? = null,
    val numberOfIngredients: Int? = null,
)
