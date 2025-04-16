package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GymHelperUseCase
import model.Meal
import java.io.File

class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        9 to this.gymHelper()
    )

    fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            println(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    fun validateOption(option: Int): Boolean {
        return option == CLIConstants.EXIT_COMMAND_CODE || option in commands.keys
    }

    // TODO: Implement your feature here as a private function and map it in the above map
    private fun gymHelper(): () -> Unit = {
        val mealCsvParser = MealCsvParser()
        val mealCsvReader = MealCsvReader(File("food.csv"))
        val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
        val gymHelperUseCase = GymHelperUseCase(mealRepository)
        println("Enter Calories:")
        val calories = readlnOrNull() ?: 0.0f
        println("Enter Protein")
        val protein = readlnOrNull() ?: 0.0f
        val matchedMeals = gymHelperUseCase.getMealsBasedOnCaloriesAndProtein(
            calories.toString().toFloat(),
            protein.toString().toFloat()
        ).chunked(5)
        matchedMeals.forEach {
            println("Matched meals: $it")
            println("Press 1 to load more matched meals")
            if ((readln().toIntOrNull() ?: 0) == 1) return@forEach
        }

    }
}