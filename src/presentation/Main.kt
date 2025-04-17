package presentation

import data.FakeMealRepositoryImp
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetMealsForLargeGroupUseCase
import logic.MealRepository
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

fun main() {
    val repository: MealRepository = MealRepositoryImpl(mealCsvParser = MealCsvParser(), mealCsvReader = MealCsvReader(File("food.csv")))
    val getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(repository)
    val cliDispatcher = CLIDispatcher(getMealsForLargeGroupUseCase)
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}