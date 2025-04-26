package presentation.features

import data.errors.NoResultException
import logic.model.Meal
import logic.usecase.GetMealsByDateUseCase
import presentation.Command
import presentation.cliController.CLIConstants
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider
import java.time.LocalDate
import java.time.format.DateTimeParseException

class GetMealsByDateFeatureUI(
    private val getMealsByDateUseCase: GetMealsByDateUseCase,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) : Command {

    override val id: Int = UserOptions.GET_MEALS_BY_DATE

    override fun execute() {
        outputHandler.printMessage(CLIConstants.ENTER_DATE_MESSAGE)
        val date = getValidDate()

        try {
            val mealsWithGivenDate = getMealsByDateUseCase.getMealsByDate(date)
            displayMeals(mealsWithGivenDate)
            val meal = getMealWithGivenId(mealsWithGivenDate)
            outputHandler.printlnMessage(meal)
        } catch (exception: NoResultException) {
            outputHandler.printlnMessage(exception.message)
        }
    }

    private fun getValidDate(): LocalDate {
        var date: LocalDate? = null
        while (date == null) {
            val userInput = userInputProvider.getUserInput() ?: ""
            try {
                date = LocalDate.parse(userInput)
            } catch (exception: DateTimeParseException) {
                outputHandler.printMessage(CLIConstants.ENTER_VALID_DATE_HINT)
            }
        }
        return date
    }

    private fun displayMeals(meals: List<Meal>) {
        meals.forEach { meal ->
            outputHandler.printlnMessage("Id: ${meal.mealId}\t Name: ${meal.mealName}")
        }
    }

    private fun getMealWithGivenId(meals: List<Meal>): Meal {
        outputHandler.printMessage(CLIConstants.ENTER_MEAL_ID)
        while (true) {
            val input = userInputProvider.getUserInput().orEmpty()
            val mealId = input.toIntOrNull()
            val selectedMeal = meals.firstOrNull { it.mealId == mealId }

            if (mealId == null || selectedMeal == null) {
                outputHandler.printMessage(CLIConstants.ENTER_VALID_ID_MESSAGE)
            } else {
                return selectedMeal
            }
        }
    }
}