package di

import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.Command
import presentation.features.*

val featuresUIModule = module {
    single { GetIraqiMealsFeatureUI(get(), get()) } bind Command::class
    single { GetRandomMealsFromCountryFeatureUI(get(), get(), get()) } bind Command::class
    single { GetItalianMealsForLargeGroupFeatureUI(get(), get()) } bind Command::class
    single { GuessPreparationTimeFeatureUI(get(), get(), get()) } bind Command::class
    single { GetRandomPotatoMealsFeatureUI(get(), get()) } bind Command::class
    single { GetMealsByDateFeatureUI(get(), get(), get()) } bind Command::class
    single { GetHighCaloriesMealsFeatureUI(get(), get(), get()) } bind Command::class
    single { GetHealthyFastMealsFeatureUI(get(), get()) } bind Command::class
    single { GetEasyFoodSuggestionsFeatureUI(get(), get()) } bind Command::class
    single { GetSeaFoodMealsSortedByProteinFeatureUI(get(), get()) } bind Command::class
    single { GuessIngredientGameFeatureUI(get(), get(), get()) } bind Command::class
    single { GymHelperFeatureUI(get(), get(), get()) } bind Command::class
    single { SearchMealByNameFeatureUI(get(), get(), get()) } bind Command::class
    single { GetSweetsWithNoEggsFeatureUI(get(), get(), get()) } bind Command::class
    single { GetKetoMealsFeatureUI(get(), get(), get()) } bind Command::class

    single<Map<Int, Command>> {
        getAll<Command>().associateBy { it.id }
    }
}