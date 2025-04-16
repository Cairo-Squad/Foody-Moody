package data

import logic.MealRepository
import model.Meal
import java.time.LocalDate

class FakeMealRepositoryImp : MealRepository {
    override fun getAllMeals(): List<Meal> {
        val listOfMeals = listOf(
            Meal(
                mealName = "italian Spaghetti Carbonara ",
                mealId = 1,
                mealDescription = "Classic Italian pasta dish with eggs, cheese, pancetta, and pepper.",
                contributorId = 101,
                minutes = 25,
                submitted = LocalDate.of(2023, 5, 10),
                tags = listOf("Italian", "Pasta", "Quick", "for-large-groups"),
                nutrition = null, // You can fill this with a `Nutrition` object
                numberOfSteps = "5",
                steps = listOf("Boil pasta", "Cook pancetta", "Mix eggs and cheese", "Combine everything", "Serve hot"),
                ingredients = listOf("Spaghetti", "Eggs", "Pancetta", "Parmesan cheese", "Black pepper"),
                numberOfIngredients = 5
            ),
            Meal(
                mealName = "Grilled Chicken Salad",
                mealId = 2,
                mealDescription = "A healthy grilled chicken salad with fresh veggies.",
                contributorId = 102,
                minutes = 15,
                submitted = LocalDate.of(2023, 6, 20),
                tags = listOf("Healthy", "Chicken", "Salad"),
                nutrition = null,
                numberOfSteps = "4",
                steps = listOf("Grill chicken", "Chop vegetables", "Mix dressing", "Combine all ingredients"),
                ingredients = listOf("Chicken breast", "Lettuce", "Tomato", "Cucumber", "Olive oil"),
                numberOfIngredients = 5
            ),
            Meal(
                mealName = "Chocolate Cake",
                mealId = 3,
                mealDescription = "A rich and moist chocolate cake.",
                contributorId = 103,
                minutes = 45,
                submitted = LocalDate.of(2023, 7, 5),
                tags = listOf("Dessert", "Chocolate", "Baking"),
                nutrition = null,
                numberOfSteps = "6",
                steps = listOf("Mix ingredients", "Preheat oven", "Pour batter", "Bake", "Cool down", "Decorate"),
                ingredients = listOf("Flour", "Cocoa powder", "Sugar", "Eggs", "Butter"),
                numberOfIngredients = 5
            )
        )
        return listOfMeals
    }


}