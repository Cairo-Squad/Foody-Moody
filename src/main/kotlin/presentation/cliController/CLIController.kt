package presentation.cliController

import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider

class CLIController(
    private val cliDispatcher: CLIDispatcher,
    private val outputHandler: OutputHandler,
    private val userInputProvider: UserInputProvider
) {

    fun start() {
        outputHandler.printlnMessage(CLIConstants.SEPARATOR)
        outputHandler.printlnMessage(CLIConstants.WELCOME_MESSAGE)
        outputHandler.printlnMessage(CLIConstants.SEPARATOR)

        while (true) {
            showMainMenu()

            val input = takeUserInput()
            if (input == UserOptions.EXIT_COMMAND_CODE) {
                break
            }

            cliDispatcher.dispatch(input)
            outputHandler.printlnMessage(CLIConstants.SEPARATOR)
        }

        outputHandler.printlnMessage(CLIConstants.SEPARATOR)
        outputHandler.printlnMessage(CLIConstants.EXIT_MESSAGE)
    }

    private fun showMainMenu() {
        outputHandler.printlnMessage(CLIConstants.USER_MENU)
    }

    private fun takeUserInput(): Int {
        outputHandler.printMessage(CLIConstants.OPTION_INPUT_MESSAGE)
        while (true) {
            val userInput = userInputProvider.getUserInput()
            val parsedNumber = userInput?.toIntOrNull()

            if (parsedNumber == null || !cliDispatcher.validateOption(parsedNumber)) {
                outputHandler.printMessage(CLIConstants.ENTER_VALID_OPTION_MESSAGE)
            } else {
                return parsedNumber
            }
        }
    }
}