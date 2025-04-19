package logic.usecase

import data.errors.NoResultException
import data.errors.ValidationException
import logic.MealRepository
import logic.model.Meal
import presentation.cliController.CLIConstants
import java.time.LocalDate
import java.time.format.DateTimeParseException

class GetMealsByDateUseCase(
    private val mealRepository: MealRepository
) {
    fun getMealsByDate(date:String): Result<List<Meal>> {
        try {
            val parsedDate = LocalDate.parse(date)
            val allMeals = mealRepository.getAllMeals()
            val filteredMeals = allMeals.filter { it.submitted == parsedDate }
            if (filteredMeals.isEmpty()) {
                return Result.failure(NoResultException(CLIConstants.NO_RESULTS_FOUND))
            }
            return Result.success(filteredMeals)
        } catch (e: DateTimeParseException) {
            return Result.failure(ValidationException(CLIConstants.ENTER_VALID_DATE_HINT))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
