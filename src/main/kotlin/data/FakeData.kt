package data

import logic.model.Meal
import logic.model.Nutrition

object FakeData {

    val allMeals = listOf(
        Meal(mealName = "fish", mealId = 1, tags = listOf("SEafood"), nutrition = Nutrition(protein = 20f)),
        Meal(mealName = "carb", mealId = 2, tags = listOf("seafood"), nutrition = Nutrition(protein = 25f)),
        Meal(mealName = "meet", mealId = 3, tags = listOf("Vegetarian"), nutrition = Nutrition(protein = 20f)),
        Meal(mealName = "egg", mealId = 4, tags = listOf("Meat"), nutrition = Nutrition(protein = 35f)),
        Meal(mealName = "Chicken", mealId = 5, tags = listOf("Meat"), nutrition = Nutrition(protein = 25f)),
        Meal(mealName = "Shrimps", mealId = 6, tags = null, nutrition = Nutrition(protein = 65f)),
        Meal(mealName = "Scallop", mealId = 7, tags = listOf("Seafood"), nutrition = null),
        Meal(mealName = "ScallopRed", mealId = 7, tags = listOf("Seafood"), nutrition = Nutrition(protein = null))
    )

    val ketoMealTest = listOf(
        Meal(
            mealId = 1,
            tags = listOf("lunch"),
            nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 18.0f)
        ),
        Meal(
            mealId = 2,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 12.0f, protein = 10.0f, totalFat = 5.0f)
        ),
        Meal(
            mealId = 3,
            tags = listOf("lunch"),
            nutrition = Nutrition(carbohydrates = 8.0f, protein = 15.0f, totalFat = 13.0f)
        ),
        Meal(
            mealId = 4,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 11.0f, protein = 14.0f, totalFat = 11.0f)
        ),
        Meal(
            mealId = 5,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 6.0f, protein = 25.0f, totalFat = 20.0f)
        ),
        Meal(mealId = 6, tags = listOf("lunch"), nutrition = null)
    )

    val testMealsOfMinProteinAndTotalFat = listOf(
        Meal(
            mealId = 1,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 5.0f, protein = 14.0f, totalFat = 15.0f)
        ),
        Meal(
            mealId = 2,
            tags = listOf("lunch"),
            nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 11.0f),
        ),
        Meal(
            mealId = 3,
            tags = listOf("lunch"),
            nutrition = Nutrition(carbohydrates = 9.5f, protein = 15.0f, totalFat = 12.0f)
        )
    )

    val nonKetoMeals = listOf(
        Meal(
            mealId = 1,
            tags = listOf("lunch"),
            nutrition = Nutrition(carbohydrates = 20.0f, protein = 10.0f, totalFat = 5.0f)
        ),
        Meal(
            mealId = 2,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 30.0f, protein = 5.0f, totalFat = 10.0f)
        )
    )
}