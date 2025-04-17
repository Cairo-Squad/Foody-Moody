package data

import logic.MealRepository
import model.Meal

class MealRepositoryImpl(
    private val dataHolder: DataHolder
) : MealRepository {

    override fun getAllMeals(): List<Meal> {
        return dataHolder.getAllMeals()
    }
}