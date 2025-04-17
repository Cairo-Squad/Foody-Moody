package presentation.cliController

object CLIConstants {

    // region User Messages
    const val WELCOME_MESSAGE = "👋🏻 Welcome to Food Change Mood!"
    const val EXIT_MESSAGE = "Thanks for your time, see you again!"

    const val SEPARATOR = "------------------------------------------------------------"

    const val OPTION_INPUT_MESSAGE = "Please enter the number of the option you need >>> "
    const val ENTER_VALID_OPTION_MESSAGE = "Please enter a valid option number >>> "

    const val COMMON_ERROR_MESSAGE = "An error happened, please try again!"
    const val GUESS_GAME_MESSAGE = "Guess The Preparation Time For This Meal : "
    const val GUESS_ERROR_MESSAGE = "invalid input please try again!"
    const val CORRECT_GUESSING_MESSAGE = "CORRECT_GUESSING ✅"
    const val TOO_LOW_GUSSING_MESSAGE = "Your guess is too low! ⬇️ Try again."
    const val TOO_HIGH_GUSSING_MESSAGE = "Your guess is too high! ⬆️ Try again."


    const val RANDOM_POTATO_MEALS_MESSAGE = "10 random meals that contain potatoes in their ingredients:"

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
            12. Get 10 random meals that contain potatoes in their ingredients
            13. 
            14. 
            15. 
            16. Exit
            
        """.trimIndent()

    // endregion

    // region Command Codes
    const val RANDOM_10_POTATO_MEALS_COMMAND_CODE = 12
    const val EXIT_COMMAND_CODE = 16

    // endregion

}