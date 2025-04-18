package presentation.cliController

import logic.GetRandomMealUseCase
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.FEATURE_5
import presentation.cliController.CLIConstants.FEATURE_3
import presentation.cliController.CLIConstants.GUESS_ERROR_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.ONE
import presentation.cliController.CLIConstants.THREE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE
import logic.*
import logic.ingredientGuess.IngredientsGameUseCase
import logic.mealSearch.SearchMealByNameUseCase
import model.Meal
import model.ShowMeal
import presentation.cliController.CLIConstants.TWO
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.KetoMealUseCase
import logic.MealRepository
import logic.SweetsNoEggsUseCase
import java.io.File

class CLIDispatcher(
    private val searchMealByName: SearchMealByNameUseCase,
    private val getIraqMeals: GetIraqMeals,
    private val randomMealUseCase: GetRandomMealUseCase,
    private val getMealsMoreThan700CaloriesUseCase: GetMealsMoreThan700CaloriesUseCase,
    private val getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase,
    private val randomPotatoMealsUseCase: RandomPotatoMealsUseCase,
    private val exploreOtherCountriesFoodCultureUseCase: ExploreOtherCountriesFoodCultureUseCase,
    private val suggestMealsToGym: SuggestMealsToGym,
    private val ingredientsGameUseCase: IngredientsGameUseCase,
    private val getRandomEasyFoodMealsUseCase: GetRandomEasyFoodMealsUseCase,
    private val getMealsByDateUseCase: GetMealsByDateUseCase,
    private val getSeafoodMealsSortedByProteinUseCase: GetSeafoodMealsSortedByProteinUseCase,
    private val getHealthyFastFoodUseCase: GetHealthyFastFoodUseCase,
    private val sweetNoeggsUsecase: SweetsNoEggsUseCase,
    private val ketoMealUseCase: KetoMealUseCase
) {
    private val commands = mapOf<Int, () -> Unit>(
        6 to { getSweetsWithNoEggs() },
        7 to { getKetoMeals() },
        CLIConstants.GET_HEALTHY_FAST_FOOD to ::getHealthyFastFoods,
        CLIConstants.GET_MEALS_BY_COUNTRY to ::getTwentyRandomMealByCountry,
        CLIConstants.GUESS_PREPARATION_TIME_GAME_COMMAND_CODE to ::guessPreparationTime,
        CLIConstants.GET_MEALS_BY_DATE to ::launchGetMealsByDate,
        CLIConstants.RANDOM_10_POTATO_MEALS_COMMAND_CODE to ::get10RandomPotatoMeals,
        CLIConstants.ITALIAN_MEALS_FOR_LARGE_GROUPS_COMMAND_CODE to ::getMealsForLargeGroup,
        CLIConstants.SUGGEST_MEAL_MORE_THAN_700_CALORIES to ::launchMealsMoreThan700Calories,
        CLIConstants.SUGGEST_MEALS_TO_GYM to ::gymHelper,
        CLIConstants.INGREDIENTS_GUESS_GAME to ::ingredientsGameGuess,
        CLIConstants.SUGGEST_TEN_EASY_FOOD_MEALS to ::launchEasyFoodSuggestionsGame,
        FEATURE_5 to ::guessPreparationTime,
        FEATURE_3 to ::displayIraqMeals,
        CLIConstants.SEARCH_MEAL_BY_NAME to ::searchMealByName,
        CLIConstants.GET_SEAFOOD_MEALS_CODE to ::getSeafoodMealsSortedByProtein
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

    fun displayIraqMeals() {
        val iraqiMeals = getIraqMeals.getIraqMeals()
        if (iraqiMeals.isEmpty()) {
            println("No Iraqi meals found.")
            return
        }

        println("🍽️ Iraqi Meals List:")
        iraqiMeals.forEach { meal ->
            println("- ${meal.mealName}")
        }
    }


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
            println("meal ${index + 1} is: $meal")
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
        while (true) {
            val userInput = UserInputHandler.getStringUserInput()
            if (userInput?.toIntOrNull() == 16) {
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
                    while (true) {
                        println(CLIConstants.ENTER_MEAL_ID)
                        val mealIdInput = UserInputHandler.getUserInput()
                        val selectedMeal = meals.firstOrNull { it.mealId == mealIdInput }
                        if (selectedMeal == null) {
                            println(CLIConstants.ID_NOT_IN_LIST)
                            continue
                        } else showMealDetails(selectedMeal)
                    }
                }
            )
        }
    }

    private fun showMealDetails(meal: Meal) {
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

    fun getHealthyFastFoods() {
        val meals = getHealthyFastFoodUseCase.getHealthyFastFood()
        println(CLIConstants.LIST_CONTAINING_HEALTHY_FAST_FOOD)
        meals.forEach {
            println(it.toString())
        }
    }


    private fun launchEasyFoodSuggestionsGame() {
        println(CLIConstants.TEN_RANDOM_EASY_FOOD_MEALS_MSG)
        getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
            .forEach(::println)
    }

    private fun getSeafoodMealsSortedByProtein() {
        try {

            val sortedMeals: List<ShowMeal> = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

            println("Seafood Meals Sorted by Protein:")
            sortedMeals.forEach { println(it.toString()) }

        } catch (e: Exception) {
            println("An error in seafood meals sorted by protein: ${e.message}")
        }

    }

    // TODO: Implement your feature here as a private function and map it in the above map
    private fun ingredientsGameGuess() {
        var bonus = 0
        for (i in 0..14) {
            val ingredientsGameResult = ingredientsGameUseCase.getRandomIngredients()
            println("Meal name : ${ingredientsGameResult.mealName}")
            println("Note that there is Only one correct ingredient of list below.")
            println("Press 1,2 or 3 to choose ingredient: ${ingredientsGameResult.allIngredients.shuffled()}")

            val userChoice = UserInputHandler.getUserInput()?.minus(1) ?: -1

            if (ingredientsGameResult.allIngredients.elementAt(index = userChoice) == ingredientsGameResult.correctIngredient) {
                println("Great , +1000 point");bonus += 1000
            } else {
                println("Sorry, correct ingredient is ${ingredientsGameResult.correctIngredient}")
                break
            }
        }
        println("Your Bonus is = $bonus")
    }

    private fun gymHelper() {
        println("Enter required Meals Calories:")
        val calories = readlnOrNull() ?: 0.0f
        println("Enter required Meals Protein")
        val protein = readlnOrNull() ?: 0.0f
        val matchedMeals = suggestMealsToGym.getMealsBasedOnCaloriesAndProtein(
            calories.toString().toFloat(),
            protein.toString().toFloat()
        ).chunked(5)

        for (fiveMeals in matchedMeals) {
            println("Top Matched Meals are : \n $fiveMeals ")
            println("Press 1 to get another matches or 0 to exist:")

            if ((UserInputHandler.getUserInput() ?: 0) == 1) continue; else break
        }

    }

    private fun searchMealByName() {
        println("Enter Meal Name: ")
        val mealName = UserInputHandler.getStringUserInput() ?: ""

        val matchedMeals = searchMealByName.searchMealByName(mealName).chunked(5)

        for (fiveMeals in matchedMeals) {
            println("Top Matched Meals are : \n $fiveMeals ")
            println("Press 1 to get another matches or 0 to exist:")

            if ((UserInputHandler.getUserInput() ?: 0) == 1) continue; else break
        }
    }

    private fun getSweetsWithNoEggs() {
        try {
            println("\n=== Sweets Without Eggs🍰🍰 ===")

            var continueSearch = true

            while (continueSearch) {
                val sweet = sweetNoeggsUsecase()
                if (sweet != null) {
                    println("\nSweet Found:")
                    println("Name: ${sweet.mealName}")
                    println("Description: ${sweet.mealDescription ?: "No description available"}")
                    println("Ingredients: ${sweet.ingredients?.joinToString(", ")}")
                    println("\nOptions:")
                    println("1. Like this sweet (show full details)")
                    println("2. Dislike (show another sweet)")
                    println("3. Back to main menu")
                    when (readlnOrNull()?.toIntOrNull()) {
                        1 -> {
                            println("\nFull Details:")
                            println(sweet)
                            println("Press Enter to continue...")
                            readlnOrNull()
                        }

                        2 -> continue // Show next sweet
                        3 -> continueSearch = false
                        else -> println("Invalid option, showing next sweet...")
                    }
                } else {
                    println("No more sweets without eggs found!")
                    println("Press Enter to continue...")
                    readlnOrNull()
                    continueSearch = false
                }

            }

        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        }


    }

    private fun getKetoMeals() {
        try {
            println("\n=== Keto-Friendly Meals 🥑🥩 ===")

            var continueSearch = true

            while (continueSearch) {
                val meal = ketoMealUseCase()
                if (meal != null) {
                    println("\nKeto Meal Found:")
                    println("Name: ${meal.mealName}")
                    println("Description: ${meal.mealDescription ?: "No description available"}")
                    println("Nutrition (per serving):")
                    println("  Calories: ${meal.nutrition?.calories ?: "N/A"}")
                    println("  Fat: ${meal.nutrition?.totalFat ?: "N/A"}g")
                    println("  Carbs: ${meal.nutrition?.carbohydrates ?: "N/A"}g")
                    println("  Protein: ${meal.nutrition?.protein ?: "N/A"}g")

                    println("\nOptions:")
                    println("1. View full details")
                    println("2. Show another keto meal")
                    println("3. Back to main menu")

                    when (readlnOrNull()?.toIntOrNull()) {
                        1 -> {
                            println("\nFull Details:")
                            println(meal)
                            println("Press Enter to continue...")
                            readlnOrNull()
                        }

                        2 -> continue
                        3 -> continueSearch = false
                        else -> println("Invalid option, showing next meal...")
                    }
                } else {
                    println("No more keto meals found!")
                    println("Press Enter to continue...")
                    readlnOrNull()
                    continueSearch = false
                }
            }
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        }
    }
}
