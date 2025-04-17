package logic

import model.Meal

class IngredientsGameUseCase(private val repository: MealRepository) {

    private var allMeals: List<Meal>

    init {
        allMeals = repository.getAllMeals()
    }

    fun ingredientsGame(): IngredientsGame {
        val randomMeal: Meal = allMeals.random()
        val popularIngredients: List<String> = listOf(
            "winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt",
            "prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese",
            "ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes",
            "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese",
            "spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon",
            "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"
        )

        val correctIngredient: String = randomMeal.ingredients?.random()!!
        val correctMealIngredients = randomMeal.ingredients

        val wrongIngredients: List<String> = popularIngredients.filter { ingredient ->
            !correctMealIngredients.contains(ingredient)
        }.take(2)

        return IngredientsGame(
            mealName = randomMeal.mealName!!,
            correctIngredient = correctIngredient,
            wrongIngredients = wrongIngredients,
            allIngredients = wrongIngredients + correctIngredient
        )
    }
}

data class IngredientsGame(
    val mealName: String,
    val correctIngredient: String,
    val wrongIngredients: List<String>,
    val allIngredients: List<String>
)