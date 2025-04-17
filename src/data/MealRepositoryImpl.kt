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


}