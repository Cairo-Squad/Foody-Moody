package presentation.features

import logic.usecase.GetHealthyFastFoodUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetHealthyFastMealsFeatureUI(
    private val getHealthyFastFoodUseCase: GetHealthyFastFoodUseCase,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.GET_HEALTHY_FAST_FOOD

    override fun execute() {
        val meals = getHealthyFastFoodUseCase.getHealthyFastFood()
        outputHandler.printlnMessage(CLIConstants.HEALTHY_FAST_FOOD_MEALS_MESSAGE)
        meals.forEach { meal ->
            outputHandler.printlnMessage(meal)
        }
    }
}