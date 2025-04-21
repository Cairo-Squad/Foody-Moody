package presentation

import data.DataSourceImpl
import logic.usecase.GetIraqMealsUseCase
import data.csvUtil.MealCsvParser
import data.csvUtil.MealCsvReader
import data.MealRepositoryImpl
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.usecase.GetRandomMealUseCase
import logic.usecase.IngredientsGameUseCase
import logic.usecase.SearchMealByNameUseCase
import logic.usecase.*
import presentation.cliController.CLIConstants
import java.io.File

fun main() {
    val mealCsvReader = MealCsvReader(File(CSVConstants.CSV_FILE_PATH))
    val mealCsvParser = MealCsvParser()

    println(CLIConstants.LOADING_MEALS_DATA_MESSAGE)
    val dataSource = DataSourceImpl(
        mealCsvParser = mealCsvParser,
        mealCsvReader = mealCsvReader
    )
    val mealRepository = MealRepositoryImpl(dataSource)

    val cliDispatcher = CLIDispatcher(
        searchMealByName = SearchMealByNameUseCase(mealRepository),
        getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(mealRepository),
        randomMealUseCase = GetRandomMealUseCase(mealRepository),
        getIraqMealsUseCase = GetIraqMealsUseCase(mealRepository),
        randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository),
        getMealsMoreThan700CaloriesUseCase = GetMealsMoreThan700CaloriesUseCase(mealRepository),
        exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(mealRepository),
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository),
        ingredientsGameUseCase = IngredientsGameUseCase(mealRepository),
        suggestMealsToGymUseCase = SuggestMealsToGymUseCase(mealRepository),
        getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository),
        getSeafoodMealsSortedByProteinUseCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository),
        getHealthyFastFoodUseCase = GetHealthyFastFoodUseCase(mealRepository),
        sweetsNoEggsUseCase = SweetsNoEggsUseCase(mealRepository),
        ketoMealUseCase = KetoMealUseCase(mealRepository),
    )

    val cliController = CLIController(cliDispatcher)
    cliController.start()
}