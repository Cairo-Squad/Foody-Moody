package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetRandomMealUseCase
import logic.GetSeafoodMealsSortedByProteinUseCase
import logic.MealRepository
import model.Meal
import model.ShowMeal
import presentation.cliController.CLIConstants.CORRECT_GUESSING_MESSAGE
import presentation.cliController.CLIConstants.GUESS_GAME_MESSAGE
import presentation.cliController.CLIConstants.TOO_HIGH_GUSSING_MESSAGE
import presentation.cliController.CLIConstants.TOO_LOW_GUSSING_MESSAGE
import java.io.File

class CLIDispatcher (private val randomMealUseCase: GetRandomMealUseCase){



    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        5 to { guessPreparationTime() },
        14 to {getSeafoodMealsSortedByProtein()}
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
   fun guessPreparationTime()
    {
        randomMealUseCase.getRandomMeal().also { meal ->
            print(GUESS_GAME_MESSAGE)
            println(meal.mealName)
            val actualTime = meal.minutes!!
            var attempts = 3
            while (attempts > 0) {
                val guessedPreparationTime = readlnOrNull()?.toIntOrNull()!!
                attempts--
                if (actualTime == guessedPreparationTime) {
                    println(CORRECT_GUESSING_MESSAGE)
                    return

                } else if (guessedPreparationTime < actualTime) {
                    println(TOO_LOW_GUSSING_MESSAGE)

                } else {
                    println(TOO_HIGH_GUSSING_MESSAGE)
                }
            }

            println("❌ Out of attempts! The correct preparation time for ${meal.mealName} is $actualTime minutes.")
        }

    }
    private fun getSeafoodMealsSortedByProtein(){
        try {
            val fileName = "food.csv"
            val csvFile = File(fileName)
            val mealCsvReader = MealCsvReader(csvFile)
            val mealCsvParser = MealCsvParser()
            val mealRepository: MealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
            val useCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository)

            val sortedMeals: List<ShowMeal> = useCase.getSeafoodMealsSortedByProtein()

            println("Seafood Meals Sorted by Protein:")
            sortedMeals.forEach {println(it.toString())}

        } catch (e: Exception) {
            println("An error in seafood meals sorted by protein: ${e.message}")
        }

    }
}