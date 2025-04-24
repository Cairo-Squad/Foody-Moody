package logic.usecase

import logic.model.Meal
import logic.MealRepository

class SweetsNoEggsUseCase(private val mealRepository: MealRepository) {
    private val suggestedSweetsIds = mutableSetOf<Int>()

    operator fun invoke(): Meal? {
        return mealRepository.getAllMeals()
            .filter { isSweetWithoutEggs(it) }
            .filterNot { suggestedSweetsIds.contains(it.mealId) }
            .randomOrNull()
            ?.also { it.mealId?.let { it1 -> suggestedSweetsIds.add(it1) } }
    }


    private fun isSweetWithoutEggs(meal: Meal): Boolean {
        return meal.tags?.any { it.contains("sweet", ignoreCase = true) } == true &&
                meal.ingredients?.any { it.contains("egg", ignoreCase = true) } != true
    }
}