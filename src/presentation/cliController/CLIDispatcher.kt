package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetMealsForLargeGroupUseCase
import model.Meal
import java.io.File

class CLIDispatcher(
    val getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase
) {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(

    )

    fun getMealsForLargeGroup(): List<Meal> {
        return getMealsForLargeGroupUseCase.getMealsForGroup()
    }

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
}