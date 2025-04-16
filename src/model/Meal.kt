package model


data class Meal(
    val mealName:String?,
    val mealId: String?,
    val mealDescription: String? = null,
    val contributorId: String? = null,
    val minutes: String? = null,
    val submitted: String? = null,
    val tags: List<String>? = null,
    val nutrition: Nutrition? = null,
    val numberOfSteps: String? = null,
    val steps: List<String>? = null,
    val ingredients: List<String>? = null,
    val numberOfIngredients: String? = null,
)
