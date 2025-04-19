package data.csvUtil

import logic.model.Meal
import logic.model.Nutrition
import java.time.LocalDate


class MealCsvParser {

    fun parseOneLine(line: String): Meal {

        val list = mutableListOf<String>()


        val firstPart = line.substring(0, line.indexOf(",\""))
        val secondPart = line.substring(line.indexOf("\""), line.length)

        val firstPartList = firstPart.split(",")
        val secondPartList = secondPart.split(",\"[", "]\",")

        firstPartList.forEach {
            list.add(it)
        }

        secondPartList.forEach { string ->
            val cleanedString = string
                .removePrefix("\"[")
                .replace("']", "")
                .replace("'", "")
                .replace("\"", "")
            list.add(cleanedString)
        }




        val meal = Meal(
            mealName = list[ColumnIndex.NAME],
            mealId = list[ColumnIndex.ID].toInt(),
            mealDescription = list[ColumnIndex.DESCRIPTION],
            contributorId = list[ColumnIndex.CONTRIBUTOR_ID].toInt(),
            minutes = list[ColumnIndex.MINUTES].toInt(),
            submitted = LocalDate.parse(list[ColumnIndex.SUBMITTED]),
            tags = structTagsList(list[ColumnIndex.TAGS]),
            nutrition = structNutritionObject(list[ColumnIndex.NUTRITION]),
            numberOfSteps = list[ColumnIndex.N_STEPS].toInt(),
            steps = structStepsList(list[ColumnIndex.STEPS]),
            ingredients = structIngredientsList(list[ColumnIndex.INGREDIENTS]),
            numberOfIngredients = list[ColumnIndex.N_INGREDIENTS].toInt()
        )

        return meal

    }


    private fun structNutritionObject(line:String): Nutrition {
        val parts = line.split(",")
        return Nutrition(
            calories = parts[0].toFloat(),
            totalFat = parts[1].toFloat(),
            sugar = parts[2].toFloat(),
            sodium = parts[3].toFloat(),
            protein = parts[4].toFloat(),
            saturatedFat = parts[5].toFloat(),
            carbohydrates = parts[6].toFloat(),

            )
    }

    private fun structTagsList(line:String):List<String>{
        val parts = line.split(",")
        return parts
    }

    private fun structStepsList(line:String):List<String>{
        val parts = line.split(",")
        return parts
    }

    private fun structIngredientsList(line:String):List<String>{
        val parts = line.split(",")
        return parts
    }
}