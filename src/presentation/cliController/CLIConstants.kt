package presentation.cliController

object CLIConstants {

    // region User Messages
    const val WELCOME_MESSAGE = "👋🏻 Welcome to your Personal Finance Tracker!"
    const val EXIT_MESSAGE = "Thanks for your time, see you again!"

    const val SEPARATOR = "------------------------------------------------------------"

    const val OPTION_INPUT_MESSAGE = "Please enter the number of the option you need >>> "
    const val ENTER_VALID_OPTION_MESSAGE = "Please enter a valid option number >>> "

    const val COMMON_ERROR_MESSAGE = "An error happened, please try again!"

    const val MEALS_MORE_THAN_700_CALORIES_WELCOME_MSG = "==== Meals with more than 700 Calories ===="
    const val NO_MORE_MEALS_AVAILABLE = "No more meals with more than 700 calories"
    const val DO_YOU_LIKE_MEAL = "Do you like it? \n" +
            " 1. like \n" +
            " 2. dislike "

    val USER_MENU = """
            Available Options:-
            1. 
            2. 
            3. 
            4. 
            5. 
            6. 
            7. 
            8. 
            9. 
            10. 
            11. 
            12. 
            13. Suggest a meal with more than 700 calories
            14. 
            15. 
            16. Exit
            
        """.trimIndent()

    // endregion

    // region Command Codes
    const val SUGGEST_MEAL_MORE_THAN_700_CALORIES = 13
    const val EXIT_COMMAND_CODE = 16

    // endregion

}