package presentation

import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.*
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
        getMealsMoreThan700CaloriesUseCase = mealsMoreThan700CaloriesUseCase,
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository)

    )
    val cliController = CLIController(cliDispatcher)
    cliController.start()
    //cliDispatcher.guessPreparationTime()

}