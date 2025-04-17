package presentation.cliController

import logic.GetSeafoodMealsSortedByProteinUseCase

object CLIConstants {

    // region User Messages
    const val WELCOME_MESSAGE = "👋🏻 Welcome to Food Change Mood!"
    const val EXIT_MESSAGE = "Thanks for your time, see you again!"

    const val SEPARATOR = "------------------------------------------------------------"

    const val OPTION_INPUT_MESSAGE = "Please enter the number of the option you need >>> "
    const val ENTER_VALID_OPTION_MESSAGE = "Please enter a valid option number >>> "
    const val ENTER_VALID_DATE = "Please enter a valid option number >>> "
    const val ENTER_MEAL_ID = "Enter meal id to show full details"
    const val ID_NOT_IN_LIST = "PLease enter id that was in the retrieved meals"

    const val COMMON_ERROR_MESSAGE = "An error happened, please try again!"
    const val NO_RESULTS_FOUND = "No result found in that date."
    const val ENTER_VALID_DATE_HINT = "please enter a valid date in format like YYYY-MM-DD"
    const val FEATURE_3 =3
    const val GUESS_GAME_MESSAGE = "Guess The Preparation Time For This Meal : "
    const val GUESS_ERROR_MESSAGE = "invalid input please try again!"
    const val CORRECT_GUESSING_MESSAGE = "CORRECT_GUESSING ✅"
    const val TOO_LOW_GUSSING_MESSAGE = "Your guess is too low! ⬇️ Try again."
    const val TOO_HIGH_GUSSING_MESSAGE = "Your guess is too high! ⬆️ Try again."
    const val ONE=1
    const val TWO=2
    const val THREE=3
    const val FEATURE_5=5


    const val RANDOM_POTATO_MEALS_MESSAGE = "10 random meals that contain potatoes in their ingredients:"
    const val TEN_RANDOM_EASY_FOOD_MEALS_MSG = "=== Ten random meals easy to prepare ==="
    const val MEALS_MORE_THAN_700_CALORIES_WELCOME_MSG = "==== Meals with more than 700 Calories ===="
    const val NO_MORE_MEALS_AVAILABLE = "No more meals with more than 700 calories"
    const val NO_DESCRIPTION_AVAILABLE = "No description available"
    const val DO_YOU_LIKE_MEAL = "Do you like it? \n" +
            " 1. like \n" +
            " 2. dislike "

    val USER_MENU = """
            Available Options:-
            1.🍔 
            2.🔍 
            3.🇮🇶 Get Iraq meals
            4.🍽️ Suggest 10 easy food meals
            5.⏱️Guess preparation time game
            6.🍪
            7.🥑
            8.📅Get meals with their added date
            9. 💪
            10.🌍Find Meals by Country
            11. 🍴
            12.🥔 Get 10 random meals that contain potatoes in their ingredients
            13.🍳Suggest a meal with more than 700 calories
            14.🐠 Get Seafood Meals Sorted By Protein 
            15.🍝Get Italian meals for large groups
            16.❌Exit
            
        """.trimIndent()

    // endregion

    // region Command Codes
    const val SUGGEST_TEN_EASY_FOOD_MEALS = 4
    const val GUESS_PREPARATION_TIME_GAME_COMMAND_CODE = 5
    const val GET_MEALS_BY_DATE = 8
    const val RANDOM_10_POTATO_MEALS_COMMAND_CODE = 12
    const val SUGGEST_MEAL_MORE_THAN_700_CALORIES = 13
    const val ITALIAN_MEALS_FOR_LARGE_GROUPS_COMMAND_CODE = 15
    const val EXIT_COMMAND_CODE = 16
    const val  GET_SEAFOOD_MEALS_CODE=14

    // endregion

    const val GET_MEALS_BY_COUNTRY = 10

}
