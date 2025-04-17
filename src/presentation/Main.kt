package presentation

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetMealsForLargeGroupUseCase
import logic.MealRepository
import logic.GetMealsMoreThan700CaloriesUseCase
import logic.GetRandomMealUseCase
import logic.RandomPotatoMealsUseCase
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

fun main() {
    val mealsFile = File("food.csv")
    val mealCsvReader = MealCsvReader(mealsFile)
    val mealCsvParser = MealCsvParser()
    val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
    val getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(mealRepository)
    val randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
    val getRandomMealUseCase = GetRandomMealUseCase(mealRepository)
    val mealsMoreThan700CaloriesUseCase = GetMealsMoreThan700CaloriesUseCase(mealRepository)
    val cliDispatcher = CLIDispatcher(
        getMealsForLargeGroupUseCase = getMealsForLargeGroupUseCase,
        randomMealUseCase = getRandomMealUseCase,
        randomPotatoMealsUseCase = randomPotatoMealsUseCase,
        getMealsMoreThan700CaloriesUseCase = mealsMoreThan700CaloriesUseCase

    )
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}