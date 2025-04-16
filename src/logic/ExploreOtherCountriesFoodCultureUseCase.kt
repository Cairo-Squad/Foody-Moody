package logic

import model.Meal

class ExploreOtherCountriesFoodCultureUseCase(
    private val repo: MealRepository
) {

    fun getTwentyRandomMealByCountry(
         countryName: String
    ): List<Meal> {
        val list = mutableListOf<Meal>()
        val searchTerm = countryName.trim().lowercase()

        repo.getAllMeals().forEach { meal ->
            if (meal.tags?.any { tag ->
                    tag.trim().lowercase() == searchTerm
                } == true) {
                list.add(meal)
            }
        }

        return list.shuffled().take(20)

    }

}