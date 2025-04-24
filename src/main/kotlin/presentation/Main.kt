package presentation

import di.appModule
import di.useCaseModule
import presentation.cliController.CLIController
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin
import presentation.cliController.CLIConstants

fun main() {
    startKoin {
        modules(appModule, useCaseModule)
    }

    println(CLIConstants.LOADING_MEALS_DATA_MESSAGE)

    val cliController: CLIController = getKoin().get()
    cliController.start()
}