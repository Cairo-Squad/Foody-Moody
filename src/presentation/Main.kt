package presentation

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetMealsForLargeGroupUseCase
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

fun main() {
    val repository = MealRepositoryImpl(mealCsvParser = MealCsvParser(), mealCsvReader = MealCsvReader(File("food.csv")))
    val getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(repository)
    val cliDispatcher = CLIDispatcher(getMealsForLargeGroupUseCase)
    val cliController = CLIController(cliDispatcher)
    //cliController.start()
    cliDispatcher.getMealsForLargeGroup().forEach { meal ->
        println(meal)
    }
}