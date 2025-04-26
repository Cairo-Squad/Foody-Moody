package presentation.features

import logic.usecase.IngredientsGameUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GuessIngredientGameFeatureUI(
    private val ingredientsGameUseCase: IngredientsGameUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.INGREDIENTS_GUESS_GAME

    override fun execute() {
        var bonus = 0
        for (i in 0..14) {
            val ingredientsGameResult = ingredientsGameUseCase.getRandomIngredients()
            outputHandler.printlnMessage("Meal name : ${ingredientsGameResult.mealName}")
            outputHandler.printlnMessage("Note that there is Only one correct ingredient of list below.")
            outputHandler.printlnMessage("Press 1,2 or 3 to choose ingredient: ${ingredientsGameResult.allIngredients.shuffled()}")

            var userChoice = userInputProvider.getUserInput()?.toIntOrNull()?.minus(1) ?: -1
            while (userChoice !in ingredientsGameResult.allIngredients.indices) {
                outputHandler.printlnMessage("Please enter 1, 2, or 3:")
                userChoice = userInputProvider.getUserInput()?.toIntOrNull()?.minus(1) ?: -1
            }

            if (ingredientsGameResult.allIngredients.elementAt(index = userChoice) == ingredientsGameResult.correctIngredient) {
                outputHandler.printlnMessage("Great , +1000 point")
                bonus += 1000
            } else {
                outputHandler.printlnMessage("Sorry, correct ingredient is ${ingredientsGameResult.correctIngredient}")
                break
            }
        }
        outputHandler.printlnMessage("Your Bonus is = $bonus")
    }
}