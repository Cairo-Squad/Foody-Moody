package presentation.features

import logic.usecase.GetHighCalorieMealsUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GetHighCaloriesMealsFeatureUI(
    private val getHighCalorieMealsUseCase: GetHighCalorieMealsUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.SUGGEST_MEAL_MORE_THAN_700_CALORIES

    override fun execute() {
        outputHandler.printlnMessage(CLIConstants.MEALS_MORE_THAN_700_CALORIES_WELCOME_MSG)
        getHighCalorieMealsUseCase.getHighCalorieMeals()
            .forEach { meal ->
                outputHandler.printlnMessage("Name: ${meal.mealName}")
                outputHandler.printlnMessage(meal.mealDescription ?: CLIConstants.NO_DESCRIPTION_AVAILABLE)
                outputHandler.printlnMessage(CLIConstants.DO_YOU_LIKE_MEAL)

                while (true) {
                    print("here: ")
                    userInputProvider.getUserInput()?.toIntOrNull()?.let {
                        when (it) {
                            CLIConstants.ONE -> {
                                outputHandler.printlnMessage(meal)
                                return
                            }

                            CLIConstants.TWO -> return@forEach
                            else -> outputHandler.printlnMessage(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
                        }
                    } ?: outputHandler.printlnMessage(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
                }
            }

        outputHandler.printlnMessage(CLIConstants.NO_MORE_MEALS_AVAILABLE)
    }
}