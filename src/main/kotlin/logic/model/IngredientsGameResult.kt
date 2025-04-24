package logic.model

data class IngredientsGameResult(
    val mealName: String,
    val correctIngredient: String,
    val wrongIngredients:List<String>,
    val allIngredients: List<String>
)