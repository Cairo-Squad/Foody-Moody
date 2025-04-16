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
    val numberOfSteps: Int? = null,
    val steps: List<String>? = null,
    val ingredients: List<String>? = null,
    val numberOfIngredients: Int? = null,
) {
    val isHighQualityMeal = mealName != null
            && mealId != null
            && mealDescription != null
            && contributorId != null
            && minutes != null
            && submitted != null
            && tags != null
            && nutrition != null
            && numberOfSteps != null
            && steps != null
            && ingredients != null
            && numberOfIngredients != null
}
