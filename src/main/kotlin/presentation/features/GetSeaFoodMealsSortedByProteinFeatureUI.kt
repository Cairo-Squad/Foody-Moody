package presentation.features

import logic.usecase.GetSeafoodMealsSortedByProteinUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetSeaFoodMealsSortedByProteinFeatureUI(
    private val getSeafoodMealsSortedByProteinUseCase: GetSeafoodMealsSortedByProteinUseCase,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.GET_SEAFOOD_MEALS_CODE

    override fun execute() {
        try {
            val sortedMeals = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()
            outputHandler.printlnMessage("Seafood Meals Sorted by Protein:")
            sortedMeals.forEach { meal -> outputHandler.printlnMessage(meal) }
        } catch (e: Exception) {
            outputHandler.printlnMessage("An error in seafood meals sorted by protein: ${e.message}")
        }
    }
}