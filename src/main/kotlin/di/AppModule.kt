package di

import data.DataSourceImpl
import data.MealRepositoryImpl
import data.csvUtil.CSVConstants
import data.csvUtil.MealCsvParser
import data.csvUtil.MealCsvReader
import logic.DataSource
import logic.MealRepository
import org.koin.dsl.module
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import presentation.ioLogic.CLIInputProvider
import presentation.ioLogic.CLIOutputHandler
import presentation.ioLogic.OutputHandler
import presentation.ioLogic.UserInputProvider
import java.io.File

val appModule = module {
    single { File(CSVConstants.CSV_FILE_PATH) }
    single { MealCsvParser() }
    single { MealCsvReader(get()) }

    single<DataSource> { DataSourceImpl(get(), get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }

    single<OutputHandler> { CLIOutputHandler() }
    single<UserInputProvider> { CLIInputProvider() }

    single {
        CLIDispatcher(
            get(),
            get()
        )
    }

    single { CLIController(get(), get(), get()) }
}