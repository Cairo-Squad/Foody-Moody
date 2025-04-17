package presentation.cliController

import logic.GetMealsForLargeGroupUseCase
import logic.GetRandomMealUseCase
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE
import logic.RandomPotatoMealsUseCase

class CLIDispatcher(
    private val getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase,
    private val randomMealUseCase: GetRandomMealUseCase,
    private val randomPotatoMealsUseCase: RandomPotatoMealsUseCase
) {

    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.GUESS_PREPARATION_TIME_GAME_COMMAND_CODE to ::guessPreparationTime,
        CLIConstants.RANDOM_10_POTATO_MEALS_COMMAND_CODE to ::get10RandomPotatoMeals,
        CLIConstants.ITALIAN_MEALS_FOR_LARGE_GROUPS_COMMAND_CODE to ::getMealsForLargeGroup,
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

    fun get10RandomPotatoMeals() {
        val random10PotatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()
        println(CLIConstants.RANDOM_POTATO_MEALS_MESSAGE)
        random10PotatoMeals.forEach(::println)
    }
}