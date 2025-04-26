package presentation.features

import logic.usecase.ExploreOtherCountriesFoodCultureUseCase
import presentation.Command
import presentation.cliController.UserOptions
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class GetRandomMealsFromCountryFeatureUI(
    private val exploreOtherCountriesFoodCultureUseCase: ExploreOtherCountriesFoodCultureUseCase,
    private val userInputProvider: UserInputProvider,
    private val outputHandler: OutputHandler
) : Command {

    override val id: Int = UserOptions.GET_MEALS_BY_COUNTRY

    override fun execute() {
        outputHandler.printlnMessage("Enter target Country: ")
        val country = userInputProvider.getUserInput()

        if (country == null) {
            outputHandler.printlnMessage("Invalid input. Please enter valid country name.")
            return
        }

        outputHandler.printlnMessage("Searching for meals from $country...")


        val matchingMeals = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry(
            countryName = country
        )

        if (matchingMeals.isEmpty()) {
            outputHandler.printlnMessage("No meals found matching \"$country\"")
        } else {
            outputHandler.printlnMessage("Found ${matchingMeals.size} matching meals:")
            matchingMeals.forEachIndexed { index, meal ->
                outputHandler.printlnMessage("${index + 1}. ${meal.mealName}. - Id: ${meal.mealId}. - Minutes: ${meal.minutes}. - Date: ${meal.submitted}. - Calories: ${meal.nutrition?.calories}., Protein: ${meal.nutrition?.protein}g.")
            }
        }
    }
}