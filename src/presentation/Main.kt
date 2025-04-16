package presentation

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

fun main() {
    val mealsFile = File("food.csv")
    val mealCsvReader = MealCsvReader(mealsFile)
    val mealCsvParser = MealCsvParser()
    val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
    val cliDispatcher = CLIDispatcher(mealRepository)
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}