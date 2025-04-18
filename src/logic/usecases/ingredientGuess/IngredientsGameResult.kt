package logic.usecases.ingredientGuess

data class IngredientsGameResult(
    val mealName: String,
    val correctIngredient: String,
    val allIngredients: List<String>
)