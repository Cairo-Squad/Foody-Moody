package logic

import model.Meal

class ExploreOtherCountriesFoodCultureUseCase(
    private val repo: MealRepository
) {

    fun getTwentyRandomMealByCountry(
         countryName: String
    ): List<Meal> {
        val randomMealsList = mutableListOf<Meal>()
        val searchTerm = countryName.trim().lowercase()

        repo.getAllMeals().forEach { meal ->
            if (meal.tags?.any { tag ->
                    tag.trim().lowercase() == searchTerm
                } == true) {
                randomMealsList.add(meal)
            }
        }

        return randomMealsList.shuffled().take(20)
    }


}