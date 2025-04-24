package data

import logic.DataSource
import logic.MealRepository
import logic.model.Meal

class MealRepositoryImpl(
    private val dataSource: DataSource
) : MealRepository {

    override fun getAllMeals(): List<Meal> {
        return dataSource.getAllMeals()
    }
}