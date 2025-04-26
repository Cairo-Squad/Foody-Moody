package presentation.features

import logic.usecase.SweetsNoEggsUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GetSweetsWithNoEggsFeatureUI(
    private val sweetsNoEggsUseCase: SweetsNoEggsUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.GET_SWEETS_WITH_NO_EGGS_COMMAND_CODE

    override fun execute() {
        try {
            outputHandler.printlnMessage("\n=== Sweets Without Eggs🍰🍰 ===")

            var continueSearch = true

            while (continueSearch) {
                val sweet = sweetsNoEggsUseCase()
                if (sweet != null) {
                    outputHandler.printlnMessage("\nSweet Found:")
                    outputHandler.printlnMessage("Name: ${sweet.mealName}")
                    outputHandler.printlnMessage("Description: ${sweet.mealDescription ?: "No description available"}")
                    outputHandler.printlnMessage("Ingredients: ${sweet.ingredients?.joinToString(", ")}")
                    outputHandler.printlnMessage("\nOptions:")
                    outputHandler.printlnMessage("1. Like this sweet (show full details)")
                    outputHandler.printlnMessage("2. Dislike (show another sweet)")
                    outputHandler.printlnMessage("3. Back to main menu")
                    when (userInputProvider.getUserInput()?.toIntOrNull()) {
                        1 -> {
                            outputHandler.printlnMessage("\nFull Details:")
                            outputHandler.printlnMessage(sweet)
                            outputHandler.printlnMessage("Press Enter to continue...")
                            userInputProvider.getUserInput()
                        }

                        2 -> continue // Show next sweet
                        3 -> continueSearch = false
                        else -> outputHandler.printlnMessage("Invalid option, showing next sweet...")
                    }
                } else {
                    outputHandler.printlnMessage("No more sweets without eggs found!")
                    outputHandler.printlnMessage("Press Enter to continue...")
                    userInputProvider.getUserInput()
                    continueSearch = false
                }

            }
        } catch (e: Exception) {
            outputHandler.printlnMessage("Error occurred: ${e.message}")
        }
    }
}