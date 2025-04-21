package di

import logic.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { SearchMealByNameUseCase(get()) }
    single { GetMealsForLargeGroupUseCase(get()) }
    single { GetRandomMealUseCase(get()) }
    single { GetIraqMealsUseCase(get()) }
    single { RandomPotatoMealsUseCase(get()) }
    single { GetMealsMoreThan700CaloriesUseCase(get()) }
    single { ExploreOtherCountriesFoodCultureUseCase(get()) }
    single { GetRandomEasyFoodMealsUseCase(get()) }
    single { IngredientsGameUseCase(get()) }
    single { SuggestMealsToGymUseCase(get()) }
    single { GetMealsByDateUseCase(get()) }
    single { GetSeafoodMealsSortedByProteinUseCase(get()) }
    single { GetHealthyFastFoodUseCase(get()) }
    single { SweetsNoEggsUseCase(get()) }
    single { KetoMealUseCase(get()) }
}