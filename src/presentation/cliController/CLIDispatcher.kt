package presentation.cliController


import data.MealCsvParser
import data.MealCsvReader
import data.MealRepositoryImpl
import logic.ExploreOtherCountriesFoodCultureUseCase
import logic.MealRepository
import java.io.File


class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        CLIConstants.GET_MEALS_BY_COUNTRY to ::getTwentyRandomMealByCountry
    )

    fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            println(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    fun validateOption(option: Int): Boolean {
        return option == CLIConstants.EXIT_COMMAND_CODE || option in commands.keys
    }

    // TODO: Implement your feature here as a private function and map it in the above map


    /**
     * this function is taking @param country from the user in run time
     * if the syntax is invalid it ask again for the input
     * and start search for 20 random meal according to the country
     */
    private fun getTwentyRandomMealByCountry() {
        println("Enter target Country: ")
        val country = readlnOrNull()

        if (country == null) {
            println("Invalid input. Please enter valid country name.")
            return
        }

        println("Searching for meals from $country...")


        val exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(initMealsRepoObject())
        val matchingMeals = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry(
            countryName = country
        )

        if (matchingMeals.isEmpty()) {
            println("No meals found matching \"$country\"")
        } else {
            println("Found ${matchingMeals.size} matching meals:")
            matchingMeals.forEachIndexed { index, meal ->
                println("${index + 1}. ${meal.mealName}. - Id: ${meal.mealId}. - Minutes: ${meal.minutes}. - Date: ${meal.submitted}. - Calories: ${meal.nutrition?.calories}., Protein: ${meal.nutrition?.protein}g.")
            }
        }
    }

    private fun initMealsRepoObject(): MealRepository{
        return MealRepositoryImpl(MealCsvParser(), MealCsvReader(File("food.csv")))
    }

}