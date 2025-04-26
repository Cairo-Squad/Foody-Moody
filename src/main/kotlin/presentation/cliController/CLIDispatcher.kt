package presentation.cliController

import presentation.Command
import presentation.ioLogic.OutputHandler

class CLIDispatcher(
    private val commandsMap: Map<Int, Command>,
    private val outputHandler: OutputHandler
) {
    fun dispatch(userInput: Int) {
        val command = commandsMap[userInput]
        if (command != null) {
            command.execute()
        } else {
            outputHandler.printlnMessage(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    fun validateOption(option: Int): Boolean {
        return option == UserOptions.EXIT_COMMAND_CODE || option in commandsMap.keys
    }
}
