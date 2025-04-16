package presentation.cliController

import logic.GetMealsMoreThan700CaloriesUseCase
import logic.GetRandomMealUseCase
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE

class CLIDispatcher (
    private val randomMealUseCase: GetRandomMealUseCase
    private val getMealsMoreThan700CaloriesUseCase: GetMealsMoreThan700CaloriesUseCase

){

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.SUGGEST_MEAL_MORE_THAN_700_CALORIES to ::launchMealsMoreThan700Calories
        5 to { guessPreparationTime() }
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
    private fun launchMealsMoreThan700Calories() {
        println(CLIConstants.MEALS_MORE_THAN_700_CALORIES_WELCOME_MSG)
        getMealsMoreThan700CaloriesUseCase.getMealMoreThan700Calories()
            .forEach { meal ->
                println("Name: ${meal.mealName}")
                println("Description: ${meal.mealDescription}")
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
   fun guessPreparationTime()
    {
        randomMealUseCase.getRandomMeal().also { meal ->
            print(GUESS_GAME_MESSAGE)
            println(meal.mealName)
            val actualTime = meal.minutes!!
            var attempts = 3
            while (attempts > 0) {
                val guessedPreparationTime = readlnOrNull()?.toIntOrNull()!!
                attempts--
                if (actualTime == guessedPreparationTime) {
                    println(CORRECT_GUESSING_MESSAGE)
                    return

                } else if (guessedPreparationTime < actualTime) {
                    println(TOO_LOW_GUSSING_MESSAGE)

                } else {
                    println(TOO_HIGH_GUSSING_MESSAGE)
                }
            }

            println("❌ Out of attempts! The correct preparation time for ${meal.mealName} is $actualTime minutes.")
        }

    }
}