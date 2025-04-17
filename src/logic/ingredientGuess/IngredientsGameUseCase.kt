package logic.ingredientGuess

import logic.MealRepository
import model.Meal

class IngredientsGameUseCase(private val mealRepository: MealRepository) {

    fun getRandomIngredients(): IngredientsGameResult {
        val allMeals = mealRepository.getAllMeals()

        val randomMeal: Meal = allMeals.random()
        val popularIngredients: List<String> = getPopularIngredients()

        val correctIngredient: String = randomMeal.ingredients?.random()!!
        val correctMealIngredients: List<String> = randomMeal.ingredients ?: emptyList()

        val wrongIngredients: List<String> = getWrongIngredients(popularIngredients, correctMealIngredients)

        return IngredientsGameResult(
            mealName = randomMeal.mealName!!,
            correctIngredient = correctIngredient,
            allIngredients = wrongIngredients + correctIngredient
        )
    }


    private fun getPopularIngredients() = listOf(
        "winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt",
        "prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese",
        "ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes",
        "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese",
        "spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon",
        "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"
    )

    private fun getWrongIngredients(
        popularIngredients: List<String>,
        correctMealIngredients: List<String>
    ) =
        popularIngredients.filter { ingredient ->
            !correctMealIngredients.contains(ingredient)
        }.shuffled().take(2)

}

