package presentation.cliController

import logic.GetIraqMeals
import model.Meal
import presentation.cliController.CLIConstants.FEATURE_3

class CLIDispatcher (
    private val getIraqMeals: GetIraqMeals
){

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        FEATURE_3  to ::displayIraqMeals,

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

    fun displayIraqMeals() {
        val iraqiMeals = getIraqMeals.getIraqMeals()
        if (iraqiMeals.isEmpty()) {
            println("No Iraqi meals found.")
            return
        }

        println("🍽️ Iraqi Meals List:")
        iraqiMeals.forEach { meal ->
            println("- ${meal.mealName }")
        }
    }

    // TODO: Implement your feature here as a private function and map it in the above map
}