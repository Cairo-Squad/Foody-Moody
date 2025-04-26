package presentation.features

import logic.usecase.GetIraqMealsUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetIraqiMealsFeatureUI(
    private val getIraqMealsUseCase: GetIraqMealsUseCase,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.GET_IRAQI_MEALS_COMMAND_CODE

    override fun execute() {
        val iraqiMeals = getIraqMealsUseCase.getIraqMeals()
        if (iraqiMeals?.isEmpty()==true) {
            outputHandler.printlnMessage("No Iraqi meals found.")
            return
        }

        outputHandler.printlnMessage("🍽️ Iraqi Meals List:")
        iraqiMeals?.forEach { meal ->
            outputHandler.printlnMessage("- ${meal.mealName}")
        }
    }
}