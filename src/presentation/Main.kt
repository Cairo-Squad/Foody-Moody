package presentation

import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher

fun main() {
    val cliDispatcher = CLIDispatcher()
    val cliController = CLIController(cliDispatcher)
    cliController.start()
}