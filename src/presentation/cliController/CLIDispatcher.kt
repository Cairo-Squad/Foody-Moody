package presentation.cliController

import model.Meal

class CLIDispatcher {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        9 to gymHelper()
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
    private val gymHelper : () -> Unit = {

        println("Enter Calories:")
        val calories = readlnOrNull() ?: 0.0f
        println("Enter Protein")
        val protein = readlnOrNull() ?: 0.0f

    }
}