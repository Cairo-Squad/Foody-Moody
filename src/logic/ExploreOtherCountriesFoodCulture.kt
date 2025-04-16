package logic

import model.Meal

class ExploreOtherCountriesFoodCulture {

    fun getTwentyRandomMealByCountry(
        meals: List<Meal>, countryName: String
    ): List<Meal> {
        val list = mutableListOf<Meal>()
        val searchTerm = countryName.trim().lowercase()

        meals.forEach { meal ->
            if (meal.tags?.any { tag ->
                    tag.trim().lowercase() == searchTerm
                } == true) {
                list.add(meal)
            }
        }

        return list.shuffled().take(20)

    }

}