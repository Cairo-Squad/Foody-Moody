package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.IngredientsGameUseCase
import java.io.File

class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        11 to ingredientsGameGuess()

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
    private fun ingredientsGameGuess(): () -> Unit = {
        val mealCsvParser = MealCsvParser()
        val mealCsvReader = MealCsvReader(File("food.csv"))
        val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
        val ingredientsGame = IngredientsGameUseCase(mealRepository)
        var bonus = 0
        for (i in 0..14) {
            val ingredientsGameResult = ingredientsGame.getRandomIngredients()
            println("Meal name : ${ingredientsGameResult.mealName}")
            println("Notice, Only one ingredient is correct.")
            println("press 1,2 or 3 to choose ingredient from below :")
            println("Ingredients are ${ingredientsGameResult.allIngredients.shuffled()}")
            var userChoice = readlnOrNull()?.toInt() ?: -1
            if (ingredientsGameResult.allIngredients.elementAt(--userChoice) == ingredientsGameResult.correctIngredient) {
                println("Great , +1000 point");bonus += 1000
            } else {
                println("Sorry, correct ingredient is ${ingredientsGameResult.correctIngredient}")
                break
            }
        }
    }

}