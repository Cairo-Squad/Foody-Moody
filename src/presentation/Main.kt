package presentation

import data.DataHolder
import logic.GetIraqMeals
import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.*
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import logic.GetRandomMealUseCase
import logic.ingredientGuess.IngredientsGameUseCase
import logic.mealSearch.SearchMealByNameUseCase
import java.io.File

fun main() {
    val mealCsvReader = MealCsvReader(File(CSVConstants.CSV_FILE_PATH))
    val mealCsvParser = MealCsvParser()
    val dataHolder = DataHolder(mealCsvParser = mealCsvParser, mealCsvReader = mealCsvReader)
    val mealRepository = MealRepositoryImpl(dataHolder)

    val cliDispatcher = CLIDispatcher(
        searchMealByName = SearchMealByNameUseCase(mealRepository),
        getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(mealRepository),
        randomMealUseCase = GetRandomMealUseCase(mealRepository),
        getIraqMeals = GetIraqMeals(mealRepository),
        randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository),
        getMealsMoreThan700CaloriesUseCase = GetMealsMoreThan700CaloriesUseCase(mealRepository),
        exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(mealRepository),
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository),
        ingredientsGameUseCase = IngredientsGameUseCase(mealRepository),
        suggestMealsToGym = SuggestMealsToGym(mealRepository),
        getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository),
        getSeafoodMealsSortedByProteinUseCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository),
        getHealthyFastFoodUseCase = GetHealthyFastFoodUseCase(mealRepository),
        sweetsNoEggsUseCase = SweetsNoEggsUseCase(mealRepository),
        ketoMealUseCase = KetoMealUseCase(mealRepository),
    )

    val cliController = CLIController(cliDispatcher)
    cliController.start()
}