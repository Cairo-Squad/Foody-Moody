package presentation.cliController

class CLIController(private val cliDispatcher: CLIDispatcher) {

    fun start() {
        println(CLIConstants.WELCOME_MESSAGE)
        println(CLIConstants.SEPARATOR)

        while (true) {
            showMainMenu()

            val input = takeUserInput()
            if (input == CLIConstants.EXIT_COMMAND_CODE) {
                break
            }

            cliDispatcher.dispatch(input)
            println(CLIConstants.SEPARATOR)
        }

        println(CLIConstants.SEPARATOR)
        println(CLIConstants.EXIT_MESSAGE)
    }

    private fun showMainMenu() {
        println(CLIConstants.USER_MENU)
    }

    private fun takeUserInput(): Int {
        print(CLIConstants.OPTION_INPUT_MESSAGE)
        while (true) {
            val userInput = readlnOrNull()
            val parsedNumber = userInput?.toIntOrNull()

            if (parsedNumber == null || !cliDispatcher.validateOption(parsedNumber)) {
                print(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
            } else {
                return parsedNumber
            }
        }
    }
}