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
        Meal(mealName = "ScallopRed", mealId = 7, tags = listOf("Seafood"), nutrition =  Nutrition(protein = null))
    )
}