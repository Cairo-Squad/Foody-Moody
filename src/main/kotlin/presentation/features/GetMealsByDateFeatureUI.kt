package presentation.features

import logic.usecase.GetMealsByDateUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GetMealsByDateFeatureUI(
    private val getMealsByDateUseCase: GetMealsByDateUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.GET_MEALS_BY_DATE

    override fun execute() {
        outputHandler.printlnMessage(CLIConstants.ENTER_DATE_MESSAGE)
        while (true) {
            val userInput = userInputProvider.getUserInput()
            if (userInput?.toIntOrNull() == 16) {
                break
            }
            val result = getMealsByDateUseCase.getMealsByDate(date = userInput!!)
            result.fold(
                onFailure = {
                    outputHandler.printlnMessage(it.message)
                },
                onSuccess = { meals ->
                    meals.forEach {
                        outputHandler.printlnMessage("Id: ${it.mealId}\t Name: ${it.mealName}")
                    }
                    while (true) {
                        outputHandler.printlnMessage(CLIConstants.ENTER_MEAL_ID)
                        val mealIdInput = userInputProvider.getUserInput()?.toIntOrNull()
                        val selectedMeal = meals.firstOrNull { it.mealId == mealIdInput }
                        if (selectedMeal == null) {
                            outputHandler.printlnMessage(CLIConstants.ID_NOT_IN_LIST)
                            continue
                        } else {
                            outputHandler.printlnMessage(selectedMeal)
                        }
                    }
                }
            )
        }
    }
}