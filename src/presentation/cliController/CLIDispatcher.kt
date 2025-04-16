package presentation.cliController

import logic.MealRepository
import logic.usecases.RandomPotatoMealsUseCase

class CLIDispatcher(
    private val mealRepository: MealRepository
) {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.RANDOM_10_POTATO_MEALS_COMMAND_CODE to ::get10RandomPotatoMeals
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
    fun get10RandomPotatoMeals() {
        val randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
        val random10PotatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()
        println("10 random meals that contain potatoes in their ingredients:")
        println(random10PotatoMeals.map { it.mealName })
    }
}