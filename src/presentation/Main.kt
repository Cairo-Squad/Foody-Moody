package presentation

import data.FakeMealRepositoryImp
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetMealsForLargeGroupUseCase
import logic.MealRepository
import logic.GetRandomMealUseCase
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

fun main() {
    val mealRepositry:MealRepository=FakeMealRepositoryImp()
    val randomMealUseCase = GetRandomMealUseCase(mealRepositry)
    val cliDispatcher = CLIDispatcher(randomMealUseCase)
    val cliController = CLIController(cliDispatcher)
    cliController.start()
    cliDispatcher.guessPreparationTime()

}