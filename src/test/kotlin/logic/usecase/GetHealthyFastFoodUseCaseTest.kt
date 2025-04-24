package logic.usecase

import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import logic.model.Nutrition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetHealthyFastFoodUseCaseTest {

    private val mealsRepository = mockk<MealRepository>()
    private lateinit var healthyFastFoodUseCase: GetHealthyFastFoodUseCase

    @BeforeEach
    fun setup() {
        healthyFastFoodUseCase = GetHealthyFastFoodUseCase(mealsRepository)
    }

    @Test
    fun `Given list of meals with null value of required minutes to prepare the meal,When filtering,Then throws IllegalArgumentException`() {
        //Given
        every { mealsRepository.getAllMeals() } returns createListOfMeals().filter {
            (((it.minutes ?: 1) > 15))
        }
        //Then
        assertThrows<IllegalArgumentException> { healthyFastFoodUseCase.getHealthyFastFood() }
    }

    @Test
    fun `Given list of meals with null value of nutrition totalFats,When filtering,Then throws IllegalArgumentException`() {
        //Given
        every { mealsRepository.getAllMeals() } returns createListOfMeals().filter { it.nutrition?.totalFat == null }
        //Then
        assertThrows<IllegalArgumentException> { healthyFastFoodUseCase.getHealthyFastFood() }
    }

    @Test
    fun `Given list of meals with null value of nutrition carbohydrates,When filtering,Then throws IllegalArgumentException`() {
        //Given
        every { mealsRepository.getAllMeals() } returns createListOfMeals().filter { it.nutrition?.carbohydrates == null }
        //Then
        assertThrows<IllegalArgumentException> { healthyFastFoodUseCase.getHealthyFastFood() }
    }

    @Test
    fun `Given list of meals with null value of nutrition saturatedFat,When filtering,Then throws IllegalArgumentException`() {
        //Given
        every { mealsRepository.getAllMeals() } returns createListOfMeals().filter { it.nutrition?.saturatedFat == null }
        //Then
        assertThrows<IllegalArgumentException> { healthyFastFoodUseCase.getHealthyFastFood() }
    }

    @Test
    fun `Given list of meals with a meal requires more than 15Min,When filtering,Then throws IllegalArgumentException`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(minutes = 20, nutrition = Nutrition(totalFat = 10f, carbohydrates = 11f, saturatedFat = 2f)),
        )
        //Then
        assertThrows<IllegalArgumentException> { healthyFastFoodUseCase.getHealthyFastFood() }
    }

    @Test
    fun `Given list of meals ,When validating,Then returns a list of valid meals meals sorted`() {
        //Given
        val meals = createListOfMeals()
        every { mealsRepository.getAllMeals() } returns meals
        //When
        val result = healthyFastFoodUseCase.getHealthyFastFood()
        //Then
        assert(result.size <= 5)
    }


    private fun createListOfMeals(): List<Meal> {
        return listOf(
            Meal(minutes = null, nutrition = Nutrition(totalFat = 1f, carbohydrates = 2f, saturatedFat = 3f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = null, carbohydrates = 5f, saturatedFat = 6f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 11f, carbohydrates = null, saturatedFat = 1f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 21f, carbohydrates = 33f, saturatedFat = null)),
            Meal(minutes = 11, nutrition = Nutrition(totalFat = 1f, carbohydrates = 2f, saturatedFat = 3f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 21f, carbohydrates = 5f, saturatedFat = 6f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 11f, carbohydrates = 21f, saturatedFat = 1f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 21f, carbohydrates = 33f, saturatedFat = 21f)),
            Meal(minutes = 10, nutrition = Nutrition(totalFat = 10f, carbohydrates = 11f, saturatedFat = 2f)),
        )
    }

}