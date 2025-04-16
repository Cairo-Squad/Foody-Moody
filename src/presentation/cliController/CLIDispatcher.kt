package presentation.cliController

import logic.GetRandomMealUseCase
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE

class CLIDispatcher (private val randomMealUseCase: GetRandomMealUseCase){

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
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