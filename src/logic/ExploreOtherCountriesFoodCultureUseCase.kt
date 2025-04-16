package logic

import model.Meal

class ExploreOtherCountriesFoodCultureUseCase(
    private val repo: MealRepository
) {

    fun getTwentyRandomMealByCountry(
         countryName: String
    ): List<Meal> {
        return repo.getTwentyRandomMealByCountry(countryName)
    }

}