package data

import logic.model.Meal
import logic.model.Nutrition

object FakeData {

    val ketoMealTest = listOf(
                Meal(mealId = 1, tags = listOf("lunch"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 18.0f)),
                Meal(mealId = 2, tags = listOf("dinner"), nutrition = Nutrition(carbohydrates = 12.0f, protein = 10.0f, totalFat = 5.0f)),
                Meal(mealId = 3, tags = listOf("lunch"), nutrition = Nutrition(carbohydrates = 8.0f, protein = 15.0f, totalFat = 13.0f)),
                Meal(mealId = 4, tags = listOf("dinner"), nutrition = Nutrition(carbohydrates = 11.0f, protein = 14.0f, totalFat = 11.0f)),
                Meal(mealId = 5, tags = listOf("dinner"), nutrition = Nutrition(carbohydrates = 6.0f, protein = 25.0f, totalFat = 20.0f)),
                Meal(mealId = 6, tags = listOf("lunch"), nutrition = null),
                Meal(mealId = 100, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 14.0f, totalFat = 15.0f)),
                Meal(mealId = 101, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 11.0f)),
                Meal(mealId = 102, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 15.0f, totalFat = 12.0f))
    )

    val testMealsOfMinProteinAndTotalFat= listOf(
        Meal(mealId = 100, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 14.0f, totalFat = 15.0f)) ,
        Meal(mealId = 101, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 11.0f),),
        Meal(mealId = 102, tags = listOf("keto"), nutrition = Nutrition(carbohydrates = 9.5f, protein = 15.0f, totalFat = 12.0f))
    )
    val nonKetoMeals = listOf(
        Meal(mealId=1, tags =  listOf("lunch"), nutrition = Nutrition(carbohydrates=20.0f,protein=  10.0f,totalFat= 5.0f)),
        Meal(mealId=2, tags =  listOf("dinner"), nutrition = Nutrition(carbohydrates=30.0f,protein=  5.0f,totalFat= 10.0f))
    )
}