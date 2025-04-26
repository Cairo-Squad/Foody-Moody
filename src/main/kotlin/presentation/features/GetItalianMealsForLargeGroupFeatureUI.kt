package presentation.features

import logic.usecase.GetMealsForLargeGroupUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetItalianMealsForLargeGroupFeatureUI(
    private val getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.ITALIAN_MEALS_FOR_LARGE_GROUPS_COMMAND_CODE

    override fun execute() {
        getMealsForLargeGroupUseCase.getAllMealsForLargeGroup().forEachIndexed { index, meal ->
            outputHandler.printlnMessage("meal ${index + 1} is: $meal")
        }
    }
}