package di

import data.DataSourceImpl
import data.MealRepositoryImpl
import data.csvUtil.MealCsvParser
import data.csvUtil.MealCsvReader
import logic.DataSource
import logic.MealRepository
import org.koin.dsl.module
import data.csvUtil.CSVConstants
import presentation.cliController.CLIController
import presentation.cliController.CLIDispatcher
import java.io.File

val appModule = module {
    single { File(CSVConstants.CSV_FILE_PATH) }
    single { MealCsvParser() }
    single { MealCsvReader(get()) }

    single<DataSource> { DataSourceImpl(get(), get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }

    single {
        CLIDispatcher(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single { CLIController(get()) }
}