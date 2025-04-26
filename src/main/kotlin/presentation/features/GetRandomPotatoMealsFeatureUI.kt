package presentation.features

import logic.usecase.RandomPotatoMealsUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetRandomPotatoMealsFeatureUI(
    private val randomPotatoMealsUseCase: RandomPotatoMealsUseCase,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.RANDOM_10_POTATO_MEALS_COMMAND_CODE

    override fun execute() {
        val random10PotatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()
        if (random10PotatoMeals.isEmpty()) {
            outputHandler.printlnMessage(CLIConstants.NO_POTATOES_MEALS_MESSAGE)
        } else {
            outputHandler.printlnMessage(CLIConstants.RANDOM_POTATO_MEALS_MESSAGE)
            random10PotatoMeals.forEach { meal ->
                outputHandler.printlnMessage(meal)
            }
        }
    }
}