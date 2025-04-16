package data

import model.Meal


class MealCsvParser {

    fun parseOneLine(line: String): Meal {
        //val cleanedLine = line.replace("\n", " ").replace("\n\n", " ")

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


//        list.forEachIndexed { index, c ->
//            println("$index -> $c")
//        }
        //println(list.size)

        val meal = Meal(
            mealName = list[ColumnIndex.NAME],
            mealId = list[ColumnIndex.ID],
            mealDescription = list[ColumnIndex.DESCRIPTION],
            contributorId = list[ColumnIndex.CONTRIBUTOR_ID],
            minutes = list[ColumnIndex.MINUTES],
            submitted = list[ColumnIndex.SUBMITTED],
            tags = null,
            nutrition = null,
            numberOfSteps = list[ColumnIndex.N_STEPS],
            steps = null,
            ingredients = null,
            numberOfIngredients = list[ColumnIndex.N_INGREDIENTS]
        )


        //println(meal)
        return meal

    }
}