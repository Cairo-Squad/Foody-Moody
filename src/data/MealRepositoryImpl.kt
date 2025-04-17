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
                val fixedMeal = meal.replace("\n", " ").replace("\n\n", " ")
                val parsedMeal = mealCsvParser.parseOneLine(fixedMeal)
                listOfMeals.add(parsedMeal)
            } catch (e: Exception) {

            }
        }
        return listOfMeals
    }

}