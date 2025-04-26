package logic.usecase

import data.errors.NoResultException
import logic.MealRepository
import logic.model.Meal
import presentation.cliController.CLIConstants
import java.time.LocalDate

class GetMealsByDateUseCase(
    private val mealRepository: MealRepository
) {
    fun getMealsByDate(date: LocalDate): List<Meal> {
        return mealRepository.getAllMeals()
            .filter { it.submitted == date }
            .takeIf { it.isNotEmpty() }
            ?: throw NoResultException(CLIConstants.NO_RESULTS_FOUND)
    }
}
