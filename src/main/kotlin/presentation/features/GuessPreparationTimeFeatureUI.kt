package presentation.features

import logic.usecase.GetRandomMealUseCase
import presentation.Command
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.GUESS_ERROR_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.TOO_HIGH_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUESSING_MESSAGE
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GuessPreparationTimeFeatureUI(
    private val randomMealUseCase: GetRandomMealUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.GUESS_PREPARATION_TIME_GAME_COMMAND_CODE

    override fun execute() {
        randomMealUseCase.getRandomMeal().also { meal ->
            outputHandler.printMessage(GUESS_GAME_MESSAGE)
            outputHandler.printlnMessage(meal?.mealName)
            val actualTime = meal?.minutes!!
            var attempts = 3
            while (attempts > 0) {
                val guessedPreparationTime = userInputProvider.getUserInput()?.toIntOrNull()
                if (guessedPreparationTime == null) {
                    outputHandler.printlnMessage(GUESS_ERROR_MESSAGE)
                    continue
                }
                attempts--

                when {
                    actualTime == guessedPreparationTime -> {
                        outputHandler.printlnMessage(CORRECT_GUESSING_MESSAGE)
                        return
                    }

                    guessedPreparationTime < actualTime -> {
                        outputHandler.printlnMessage(TOO_LOW_GUESSING_MESSAGE)
                    }

                    else -> {
                        outputHandler.printlnMessage(TOO_HIGH_GUESSING_MESSAGE)
                    }
                }
            }

            outputHandler.printlnMessage("❌ Out of attempts! The correct preparation time for ${meal.mealName} is $actualTime minutes.")
        }
    }
}