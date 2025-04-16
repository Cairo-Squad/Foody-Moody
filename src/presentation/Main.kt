package presentation

import data.FakeMealRepositoryImp
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.GetRandomMealUseCase
import logic.MealRepository


fun main() {
    val mealRepositry:MealRepository=FakeMealRepositoryImp()
    val randomMealUseCase = GetRandomMealUseCase(mealRepositry)
    val cliDispatcher = CLIDispatcher(randomMealUseCase)
    val cliController = CLIController(cliDispatcher)
    cliController.start()
    cliDispatcher.guessPreparationTime()

}