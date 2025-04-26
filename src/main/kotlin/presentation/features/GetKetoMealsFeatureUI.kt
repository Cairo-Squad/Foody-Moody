package presentation.features

import logic.usecase.KetoMealUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GetKetoMealsFeatureUI(
    private val ketoMealUseCase: KetoMealUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {
    override val id: Int = UserOptions.GET_KETO_FRIENDLY_MEALS_COMMAND_CODE

    override fun execute() {
        try {
            outputHandler.printlnMessage("\n=== Keto-Friendly Meals 🥑🥩 ===")

            var continueSearch = true

            while (continueSearch) {
                val meal = ketoMealUseCase()
                if (meal != null) {
                    outputHandler.printlnMessage("\nKeto Meal Found:")
                    outputHandler.printlnMessage("Name: ${meal.mealName}")
                    outputHandler.printlnMessage("Description: ${meal.mealDescription ?: "No description available"}")
                    outputHandler.printlnMessage("Nutrition (per serving):")
                    outputHandler.printlnMessage("  Calories: ${meal.nutrition?.calories ?: "N/A"}")
                    outputHandler.printlnMessage("  Fat: ${meal.nutrition?.totalFat ?: "N/A"}g")
                    outputHandler.printlnMessage("  Carbs: ${meal.nutrition?.carbohydrates ?: "N/A"}g")
                    outputHandler.printlnMessage("  Protein: ${meal.nutrition?.protein ?: "N/A"}g")

                    outputHandler.printlnMessage("\nOptions:")
                    outputHandler.printlnMessage("1. View full details")
                    outputHandler.printlnMessage("2. Show another keto meal")
                    outputHandler.printlnMessage("3. Back to main menu")

                    when (userInputProvider.getUserInput()?.toIntOrNull()) {
                        1 -> {
                            outputHandler.printlnMessage("\nFull Details:")
                            outputHandler.printlnMessage(meal)
                            outputHandler.printlnMessage("Press Enter to continue...")
                            userInputProvider.getUserInput()
                        }

                        2 -> continue
                        3 -> continueSearch = false
                        else -> outputHandler.printlnMessage("Invalid option, showing next meal...")
                    }
                } else {
                    outputHandler.printlnMessage("No more keto meals found!")
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