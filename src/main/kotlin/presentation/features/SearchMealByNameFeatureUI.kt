package presentation.features

import logic.usecase.SearchMealByNameUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class SearchMealByNameFeatureUI(
    private val searchMealByNameUseCase: SearchMealByNameUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.SEARCH_MEAL_BY_NAME

    override fun execute() {
        outputHandler.printlnMessage("Enter Meal Name: ")
        val mealName = userInputProvider.getUserInput() ?: ""

        val matchedMeals = searchMealByNameUseCase.searchMealByName(mealName).chunked(5)

        for (fiveMeals in matchedMeals) {
            outputHandler.printlnMessage("Top Matched Meals are : \n $fiveMeals ")
            outputHandler.printlnMessage("Press 1 to get another matches or 0 to exist:")

            if ((userInputProvider.getUserInput()?.toIntOrNull() ?: 0) == 1) continue; else break
        }
    }
}