package presentation.cliController

import logic.MealRepository
import logic.usecases.RandomPotatoMealsUseCase

class CLIDispatcher(
    private val mealRepository: MealRepository
) {

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

    fun get10RandomPotatoMeals() {
        val randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
        val random10PotatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()
        println(CLIConstants.RANDOM_POTATO_MEALS_MESSAGE)
        random10PotatoMeals.forEach(::println)
    }
}