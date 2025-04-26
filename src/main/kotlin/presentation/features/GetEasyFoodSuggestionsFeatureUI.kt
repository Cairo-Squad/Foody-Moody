package presentation.features

import logic.usecase.GetRandomEasyFoodMealsUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler

class GetEasyFoodSuggestionsFeatureUI(
    private val getRandomEasyFoodMealsUseCase: GetRandomEasyFoodMealsUseCase,
    private val outputHandler: OutputHandler
) : Command {
    override val id: Int = UserOptions.SUGGEST_TEN_EASY_FOOD_MEALS

    override fun execute() {
        try {
            outputHandler.printlnMessage(CLIConstants.TEN_RANDOM_EASY_FOOD_MEALS_MSG)
            getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
                .forEach(::println)
        } catch (exception: Exception) {
            outputHandler.printlnMessage(exception.message)
        }
    }
}