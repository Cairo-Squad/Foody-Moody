package presentation.cliController

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.KetoMealUseCase
import logic.MealRepository
import logic.SweetsNoEggsUseCase
import model.Meal
import java.io.File

class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        6 to {getSweetsWithNoEggs()},
        7 to {getKetoMeals()},

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
private fun getSweetsWithNoEggs(){
    try{
        val csvFile = File( "food.csv")
        val mealCsvReader = MealCsvReader(csvFile)
        val mealCsvParser = MealCsvParser()
        val mealRepository :MealRepository = MealRepositoryImpl(mealCsvParser,mealCsvReader)

        val sweetNoeggsUsecase = SweetsNoEggsUseCase(mealRepository)

        println("\n=== Sweets Without Eggs🍰🍰 ===")

        var continueSearch = true

        while (continueSearch){
            val sweet = sweetNoeggsUsecase()
            if(sweet!=null){
                println("\nSweet Found:")
                println("Name: ${sweet.mealName}")
                println("Description: ${sweet.mealDescription ?: "No description available"}")
                println("Ingredients: ${sweet.ingredients?.joinToString(", ")}")
                println("\nOptions:")
                println("1. Like this sweet (show full details)")
                println("2. Dislike (show another sweet)")
                println("3. Back to main menu")
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {
                        println("\nFull Details:")
                        println(sweet)
                        println("Press Enter to continue...")
                        readlnOrNull()
                    }
                    2 -> continue // Show next sweet
                    3 -> continueSearch = false
                    else -> println("Invalid option, showing next sweet...")
                }
            } else {
                println("No more sweets without eggs found!")
                println("Press Enter to continue...")
                readlnOrNull()
                continueSearch = false
            }

            }

    }catch (e: Exception){
        println("Error occurred: ${e.message}")
    }


}

    private fun getKetoMeals() {
        try {
            val csvFile = File("food.csv")
            val mealCsvReader = MealCsvReader(csvFile)
            val mealCsvParser = MealCsvParser()
            val mealRepository: MealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)

            val ketoMealUseCase = KetoMealUseCase(mealRepository)

            println("\n=== Keto-Friendly Meals 🥑🥩 ===")

            var continueSearch = true

            while (continueSearch) {
                val meal = ketoMealUseCase()
                if (meal != null) {
                    println("\nKeto Meal Found:")
                    println("Name: ${meal.mealName}")
                    println("Description: ${meal.mealDescription ?: "No description available"}")
                    println("Nutrition (per serving):")
                    println("  Calories: ${meal.nutrition?.calories ?: "N/A"}")
                    println("  Fat: ${meal.nutrition?.totalFat ?: "N/A"}g")
                    println("  Carbs: ${meal.nutrition?.carbohydrates ?: "N/A"}g")
                    println("  Protein: ${meal.nutrition?.protein ?: "N/A"}g")

                    println("\nOptions:")
                    println("1. View full details")
                    println("2. Show another keto meal")
                    println("3. Back to main menu")

                    when (readlnOrNull()?.toIntOrNull()) {
                        1 -> {
                            println("\nFull Details:")
                            println(meal)
                            println("Press Enter to continue...")
                            readlnOrNull()
                        }
                        2 -> continue
                        3 -> continueSearch = false
                        else -> println("Invalid option, showing next meal...")
                    }
                } else {
                    println("No more keto meals found!")
                    println("Press Enter to continue...")
                    readlnOrNull()
                    continueSearch = false
                }
            }
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        }
    }





}