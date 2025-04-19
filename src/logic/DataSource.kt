package logic

import logic.model.Meal

interface DataSource {
    fun getAllMeals(): List<Meal>
}