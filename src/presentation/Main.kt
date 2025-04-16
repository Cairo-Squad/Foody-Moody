package presentation

//import data.FakeMealRepositoryImp
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.GetRandomEasyFoodMealsUseCase
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.GetRandomMealUseCase
import logic.MealRepository
import java.io.File


fun main() {
    val file = File("food.csv")
    val parser = MealCsvParser()
    val reader = MealCsvReader(file)

    val mealRepositry:MealRepository=MealRepositoryImpl(parser, reader)
    val randomMealUseCase = GetRandomMealUseCase(mealRepositry)
    val getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepositry)
    val cliDispatcher = CLIDispatcher(
        getRandomEasyFoodMealsUseCase,
        randomMealUseCase
    )
    val cliController = CLIController(cliDispatcher)
    cliController.start()
    cliDispatcher.guessPreparationTime()

}