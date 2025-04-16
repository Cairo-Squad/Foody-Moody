package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.mealSearch.SearchMealByNameUseCase
import java.io.File

class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(

        2 to searchMealByName()

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

    private fun searchMealByName(): () -> Unit {
        val mealCsvParser = MealCsvParser()
        val mealCsvReader = MealCsvReader(File("food.csv"))
        val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
        val searchMealByNameUseCase = SearchMealByNameUseCase(mealRepository)
        return {
            println("Enter Meal Name: ")
            val mealName = readlnOrNull() ?: ""
            println("Press 1 to ignore uppercase or press 2 not to ignore uppercase:")
            val ignoreCase = (readlnOrNull()?.toInt() ?: 1) == 1
            val matchedMeals = searchMealByNameUseCase.searchMealByName(mealName, ignoreCase).chunked(5)
            for ((index, meals) in matchedMeals.withIndex()) {
                println("\n Top Matched Meals are : \n $meals ")
                println("\n Press 1 to get another matches or 0 to exist:")
                if ((readlnOrNull() ?: 0) == 1) {
                    if (matchedMeals[index.plus(1)].isEmpty()) {
                        println("sorry no more matched meals")
                        break
                    } else
                        continue
                } else break
            }
        }
    }
}