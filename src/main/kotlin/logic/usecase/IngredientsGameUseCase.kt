package logic.usecase

import logic.MealRepository
import logic.model.IngredientsGameResult
import logic.model.Meal

class IngredientsGameUseCase(private val mealRepository: MealRepository) {

    fun getRandomIngredients(): IngredientsGameResult {
        val allMeals = mealRepository.getAllMeals().filter(::checkMealContainsNameAndIngredients)

        check(allMeals.isNotEmpty())
        { "No valid meals with ingredients and name found" }

        val randomMeal: Meal = allMeals.random()

        val correctMealIngredients: List<String> = randomMeal.ingredients!!
        val correctMealIngredient = randomMeal.ingredients.random()

        val wrongIngredients = get2WrongIngredients(getFakeIngredients(), correctMealIngredients)

        return IngredientsGameResult(
            mealName = randomMeal.mealName!!,
            correctIngredient = correctMealIngredient,
            wrongIngredients = wrongIngredients,
            allIngredients = wrongIngredients + correctMealIngredient
        )
    }

    private fun checkMealContainsNameAndIngredients(meal: Meal) =
        !meal.ingredients.isNullOrEmpty() && !meal.mealName.isNullOrBlank()


    private fun getFakeIngredients() = listOf(
        "winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt",
        "prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese",
        "ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes",
        "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese",
        "spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon",
        "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"
    )

    private fun get2WrongIngredients(
        popularIngredients: List<String>,
        correctMealIngredients: List<String>
    ): List<String> {
        val pureWrongIngredients = popularIngredients
            .filter { ingredient ->
                !correctMealIngredients.contains(ingredient)
            }.takeIf { it.isNotEmpty() }
            ?: throw IllegalStateException("all meal ingredients matches with the wrong ingredients database")
        return pureWrongIngredients
            .shuffled()
            .take(NUMBER_OF_REQUIRED_WRONG_INGREDIENTS)
            .takeIf { it.size == NUMBER_OF_REQUIRED_WRONG_INGREDIENTS }
            ?: throw IllegalStateException("Not enough wrong ingredients to proceed. Found only ${pureWrongIngredients.size}.")

    }

    companion object {
        private const val NUMBER_OF_REQUIRED_WRONG_INGREDIENTS = 2
    }

}

