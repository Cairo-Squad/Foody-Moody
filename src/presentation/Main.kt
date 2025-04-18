package presentation

import data.DataHolder
import logic.usecases.GetIraqMealsUseCase
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.usecases.GetRandomMealUseCase
import logic.usecases.ingredientGuess.IngredientsGameUseCase
import logic.usecases.mealSearch.SearchMealByNameUseCase
import logic.usecases.*
import presentation.cliController.CLIConstants
import java.io.File

fun main() {
    val mealCsvReader = MealCsvReader(File(CSVConstants.CSV_FILE_PATH))
    val mealCsvParser = MealCsvParser()

    println(CLIConstants.LOADING_MEALS_DATA_MESSAGE)
    val dataHolder = DataHolder(
        mealCsvParser = mealCsvParser,
        mealCsvReader = mealCsvReader
    )
    val mealRepository = MealRepositoryImpl(dataHolder)

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