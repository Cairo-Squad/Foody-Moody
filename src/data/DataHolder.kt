package data

import model.Meal

class DataHolder(
    private val mealCsvParser: MealCsvParser,
    private val mealCsvReader: MealCsvReader
) {
    private var listOfMeals: List<Meal>? = null

    fun getAllMeals(): List<Meal> {
        if (listOfMeals != null) {
            return listOfMeals!!
        }

        val list = mutableListOf<Meal>()
        mealCsvReader.readCsvLines().forEachIndexed { i, meal ->
            try {
                val cleanedLine = meal.replace("\n", " ")
                    .replace("\n\n", " ")
                    .replace("*", "")
                    .replace(",['", ",\"['")
                    .replace("'],", "']\",")
                val parsedMeal = mealCsvParser.parseOneLine(cleanedLine)
                list.add(parsedMeal)
            } catch (e: Exception) {
                list.add(Meal(null, null))
            }
        }

        listOfMeals = list
        return listOfMeals!!
    }
}