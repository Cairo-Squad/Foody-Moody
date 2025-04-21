package logic.model

data class IngredientsGameResult(
    val mealName: String,
    val correctIngredient: String,
    val allIngredients: List<String>
)