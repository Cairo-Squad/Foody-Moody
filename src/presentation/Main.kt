package presentation
import logic.GetIraqMeals
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.*
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.GetRandomMealUseCase
import java.io.File


fun main() {
    val mealsFile = File("food.csv")
    val mealCsvReader = MealCsvReader(mealsFile)
    val mealCsvParser = MealCsvParser()
    val mealRepository = MealRepositoryImpl(mealCsvParser, mealCsvReader)
    val getIraqMeals = GetIraqMeals(mealRepository)
    val getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(mealRepository)
    val randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
    val getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository)
    val getRandomMealUseCase = GetRandomMealUseCase(mealRepository)
    val mealsMoreThan700CaloriesUseCase = GetMealsMoreThan700CaloriesUseCase(mealRepository)
    val exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(mealRepository)
    val cliDispatcher = CLIDispatcher(
        getMealsForLargeGroupUseCase = getMealsForLargeGroupUseCase,
        randomMealUseCase = getRandomMealUseCase,
        getIraqMeals = getIraqMeals,
        randomPotatoMealsUseCase = randomPotatoMealsUseCase,
        getMealsMoreThan700CaloriesUseCase = mealsMoreThan700CaloriesUseCase,
        exploreOtherCountriesFoodCultureUseCase = exploreOtherCountriesFoodCultureUseCase,
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository),
        getMealsByDateUseCase = getMealsByDateUseCase
    )
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}