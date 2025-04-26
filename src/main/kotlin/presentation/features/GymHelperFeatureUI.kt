package presentation.features

import logic.usecase.SuggestMealsToGymUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GymHelperFeatureUI(
    private val suggestMealsToGymUseCase: SuggestMealsToGymUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.SUGGEST_MEALS_TO_GYM

    override fun execute() {
        outputHandler.printlnMessage("Enter required Meals Calories:")
        val calories = userInputProvider.getUserInput() ?: 0.0f
        outputHandler.printlnMessage("Enter required Meals Protein")
        val protein = userInputProvider.getUserInput() ?: 0.0f
        val matchedMeals = suggestMealsToGymUseCase.getMatchedMeals(
            calories.toString().toFloat(),
            protein.toString().toFloat()
        ).chunked(5)

        for (fiveMeals in matchedMeals) {
            outputHandler.printlnMessage("Top Matched Meals are : \n $fiveMeals ")
            outputHandler.printlnMessage("Press 1 to get another matches or 0 to exist:")

            if ((userInputProvider.getUserInput()?.toIntOrNull() ?: 0) == 1) continue; else break
        }
    }
}