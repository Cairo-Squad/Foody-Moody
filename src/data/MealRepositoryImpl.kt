package data

import logic.MealRepository
import model.Meal

class MealRepositoryImpl(
    private val mealCsvParser: MealCsvParser,
    private val mealCsvReader: MealCsvReader
): MealRepository {
    override fun getAllMeals(): List<Meal> {
        val listOfMeals = mutableListOf<Meal>()
        mealCsvReader.readCsvLines().forEachIndexed { i, meal ->
            try {
                val cleanedLine = meal.replace("\n", " ")
                    .replace("\n\n", " ")
                    .replace("*", "")
                    .replace(",['", ",\"['")
                    .replace("'],", "']\",")
                val parsedMeal = mealCsvParser.parseOneLine(cleanedLine)
                listOfMeals.add(parsedMeal)
            } catch (e: Exception) {
//                println("Failed to parse line #$i")
//                println("Reason: ${e.message}")
            }
        }
        return listOfMeals
    }


    override fun getTwentyRandomMealByCountry(countryName: String): List<Meal> {
        val list = mutableListOf<Meal>()
        val searchTerm = countryName.trim().lowercase()

        getAllMeals().forEach { meal ->
            if (meal.tags?.any { tag ->
                    tag.trim().lowercase() == searchTerm
                } == true) {
                list.add(meal)
            }
        }

        return list.shuffled().take(20)
    }

}