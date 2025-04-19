package data

import data.csvUtil.MealCsvParser
import data.csvUtil.MealCsvReader
import logic.DataSource
import logic.model.Meal

class DataSourceImpl(
    private val mealCsvParser: MealCsvParser,
    private val mealCsvReader: MealCsvReader
) : DataSource {
    private var listOfMeals: List<Meal> = emptyList()

    init {
        getAllMeals()
    }

    override fun getAllMeals(): List<Meal> {
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