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
    val mealsFile = File("food.csv")
    val mealCsvReader = MealCsvReader(mealsFile)
    val mealCsvParser = MealCsvParser()
    val dataHolder = DataHolder(mealCsvParser, mealCsvReader)
    val mealRepository = MealRepositoryImpl(dataHolder)
    val searchMealsByName = SearchMealByNameUseCase(mealsRepository = mealRepository)
    val getIraqMeals = GetIraqMeals(mealRepository)
    val getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(mealRepository)
    val randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
    val ingredientsGameUseCase = IngredientsGameUseCase(mealRepository = mealRepository)
    val suggestMealsToGym = SuggestMealsToGym(mealRepository = mealRepository)
    val getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository)
    val getRandomMealUseCase = GetRandomMealUseCase(mealRepository)
    val mealsMoreThan700CaloriesUseCase = GetMealsMoreThan700CaloriesUseCase(mealRepository)
    val exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(mealRepository)
    val cliDispatcher = CLIDispatcher(
        searchMealByName = searchMealsByName,
        getMealsForLargeGroupUseCase = getMealsForLargeGroupUseCase,
        randomMealUseCase = getRandomMealUseCase,
        getIraqMeals = getIraqMeals,
        randomPotatoMealsUseCase = randomPotatoMealsUseCase,
        getMealsMoreThan700CaloriesUseCase = mealsMoreThan700CaloriesUseCase,
        exploreOtherCountriesFoodCultureUseCase = exploreOtherCountriesFoodCultureUseCase,
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository),
        ingredientsGameUseCase = ingredientsGameUseCase,
        suggestMealsToGym = suggestMealsToGym,
        getMealsByDateUseCase = getMealsByDateUseCase,
        getSeafoodMealsSortedByProteinUseCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository)
    )
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}