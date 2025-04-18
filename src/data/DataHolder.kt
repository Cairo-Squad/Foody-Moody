package data

import model.Meal

class DataHolder(
    private val mealCsvParser: MealCsvParser,
    private val mealCsvReader: MealCsvReader
) {
    private var listOfMeals: List<Meal> = emptyList()

    init {
        getAllMeals()
    }

    fun getAllMeals(): List<Meal> {
        if (listOfMeals.isNotEmpty()) {
            return listOfMeals
        }

        val tempList = mutableListOf<Meal>()
        mealCsvReader.readCsvLines().forEachIndexed { i, meal ->
            try {
                val cleanedLine = meal.replace("\n", " ")
                    .replace("\n\n", " ")
                    .replace("*", "")
                    .replace(",['", ",\"['")
                    .replace("'],", "']\",")
                val parsedMeal = mealCsvParser.parseOneLine(cleanedLine)
                tempList.add(parsedMeal)
            } catch (e: Exception) {
                tempList.add(Meal(null, null))
            }
        }

        listOfMeals = tempList
        return listOfMeals
    }
}