package presentation.cliController
import logic.GetRandomMealUseCase
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.FEATURE_5
import presentation.cliController.CLIConstants.GUESS_ERROR_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.ONE
import presentation.cliController.CLIConstants.THREE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE
import logic.*
import model.Meal
import presentation.cliController.CLIConstants.TWO
class CLIDispatcher (
    private val randomMealUseCase: GetRandomMealUseCase,
    private val getMealsMoreThan700CaloriesUseCase: GetMealsMoreThan700CaloriesUseCase,
    private val getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase,
    private val randomPotatoMealsUseCase: RandomPotatoMealsUseCase,
    private val exploreOtherCountriesFoodCultureUseCase: ExploreOtherCountriesFoodCultureUseCase,
    private val getRandomEasyFoodMealsUseCase: GetRandomEasyFoodMealsUseCase,
    private val getMealsByDateUseCase: GetMealsByDateUseCase,
) {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.GET_MEALS_BY_COUNTRY to ::getTwentyRandomMealByCountry,
        CLIConstants.GUESS_PREPARATION_TIME_GAME_COMMAND_CODE to ::guessPreparationTime,
        CLIConstants.GET_MEALS_BY_DATE to ::launchGetMealsByDate,
        CLIConstants.RANDOM_10_POTATO_MEALS_COMMAND_CODE to ::get10RandomPotatoMeals,
        CLIConstants.ITALIAN_MEALS_FOR_LARGE_GROUPS_COMMAND_CODE to ::getMealsForLargeGroup,
        CLIConstants.SUGGEST_MEAL_MORE_THAN_700_CALORIES to ::launchMealsMoreThan700Calories,
        CLIConstants.SUGGEST_TEN_EASY_FOOD_MEALS to ::launchEasyFoodSuggestionsGame,
       FEATURE_5 to ::guessPreparationTime
    )

    fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            println(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    fun validateOption(option: Int): Boolean {
        return option == CLIConstants.EXIT_COMMAND_CODE || option in commands.keys
    }

    // TODO: Implement your feature here as a private function and map it in the above map


    /**
     * this function is taking @param country from the user in run time
     * if the syntax is invalid it ask again for the input
     * and start search for 20 random meal according to the country
     */
    private fun getTwentyRandomMealByCountry() {
        println("Enter target Country: ")
        val country = readlnOrNull()

        if (country == null) {
            println("Invalid input. Please enter valid country name.")
            return
        }

        println("Searching for meals from $country...")


        val matchingMeals = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry(
            countryName = country
        )

        if (matchingMeals.isEmpty()) {
            println("No meals found matching \"$country\"")
        } else {
            println("Found ${matchingMeals.size} matching meals:")
            matchingMeals.forEachIndexed { index, meal ->
                println("${index + 1}. ${meal.mealName}. - Id: ${meal.mealId}. - Minutes: ${meal.minutes}. - Date: ${meal.submitted}. - Calories: ${meal.nutrition?.calories}., Protein: ${meal.nutrition?.protein}g.")
            }
        }
    }


    fun getMealsForLargeGroup() {
        getMealsForLargeGroupUseCase.getAllMealsForLargeGroup().forEachIndexed { index, meal ->
            println("meal $index is: $meal")
        }
    }

   private fun guessPreparationTime() {
        randomMealUseCase.getRandomMeal().also { meal ->
            print(GUESS_GAME_MESSAGE)
            println(meal.mealName)
            val actualTime = meal.minutes!!
            var attempts = 3
            while (attempts > 0) {
                val guessedPreparationTime = readlnOrNull()?.toIntOrNull()
                if (guessedPreparationTime == null) {
                    println(GUESS_ERROR_MESSAGE)
                    continue
                }
                attempts--

                when {
                    actualTime == guessedPreparationTime -> when (ONE) {
                        ONE -> {
                            println(CORRECT_GUESSING_MESSAGE)
                            return
                        }
                    }
                    guessedPreparationTime < actualTime -> when (TWO) {
                        TWO -> println(TOO_LOW_GUSSING_MESSAGE)
                    }
                    else -> when (THREE) {
                        THREE -> println(TOO_HIGH_GUSSING_MESSAGE)
                    }
                }
            }

            println("❌ Out of attempts! The correct preparation time for ${meal.mealName} is $actualTime minutes.")
        }
    }

    fun get10RandomPotatoMeals() {
        val random10PotatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()
        println(CLIConstants.RANDOM_POTATO_MEALS_MESSAGE)
        random10PotatoMeals.forEach(::println)
    }

    private fun launchMealsMoreThan700Calories() {
        println(CLIConstants.MEALS_MORE_THAN_700_CALORIES_WELCOME_MSG)
        getMealsMoreThan700CaloriesUseCase.getMealMoreThan700Calories()
            .forEach { meal ->
                println("Name: ${meal.mealName}")
                meal.mealDescription?.let { description ->
                    println("Description: $description")
                } ?: println(CLIConstants.NO_DESCRIPTION_AVAILABLE)
                println(CLIConstants.DO_YOU_LIKE_MEAL)

                while (true) {
                    print("here: ")
                    UserInputHandler.getUserInput()?.let {
                        when (it) {
                            1 -> {
                                println(meal.toString())
                                return
                            }

                            2 -> return@forEach
                            else -> println(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
                        }
                    } ?: println(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
                }
            }

        println(CLIConstants.NO_MORE_MEALS_AVAILABLE)
    }

    fun launchGetMealsByDate() {
        println(CLIConstants.ENTER_VALID_DATE)
        while(true){
            val userInput = UserInputHandler.getStringUserInput()
            if(userInput?.toIntOrNull() == 16){
                break
            }
            val result = getMealsByDateUseCase.getMealsByDate(date = userInput!!)
            result.fold(
                onFailure = {
                    println(it.message)
                },
                onSuccess = { meals ->
                    meals.forEach {
                        println("Id: ${it.mealId}\t Name: ${it.mealName}")
                    }
                    while(true) {
                        println(CLIConstants.ENTER_MEAL_ID)
                        val mealIdInput = UserInputHandler.getUserInput()
                        val selectedMeal = meals.firstOrNull { it.mealId == mealIdInput }
                        if(selectedMeal == null){
                            println(CLIConstants.ID_NOT_IN_LIST)
                            continue
                        }else showMealDetails(selectedMeal)
                    }
                }
            )
        }
    }

    private fun showMealDetails(meal : Meal){
        println("Meal Id: ${meal.mealId}")
        println("Meal name: ${meal.mealName}")
        println("Meal description: ${meal.mealDescription}")
        println("Meal preparation time: ${meal.minutes}")
        println("Meal number Of Ingredients: ${meal.numberOfIngredients}")
        println("Meal ingredients: ${meal.ingredients}")
        println("Meal steps: ${meal.steps}")
        println("Meal tags: ${meal.tags}")
        println("Meal contributorId: ${meal.contributorId}")
        println("Meal publish date: ${meal.submitted}")
    }

    private fun launchEasyFoodSuggestionsGame() {
        println(CLIConstants.TEN_RANDOM_EASY_FOOD_MEALS_MSG)
        getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
            .forEach(::println)
    }
}