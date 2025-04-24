package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.FakeData
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import logic.model.Nutrition
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class KetoMealUseCaseTest {
    private lateinit var repository : MealRepository
    private lateinit var ketoMealUseCase : KetoMealUseCase

    @BeforeEach
    fun setup() {
        repository = mockk(relaxed = true)
        ketoMealUseCase = KetoMealUseCase(repository)
    }

    @Test
    fun `should return null when no keto meals are available with rule keto`() {
        // Given
        val nonKetoMeals = FakeData.nonKetoMeals
        every { repository.getAllMeals() } returns nonKetoMeals

        // When
        val result = ketoMealUseCase.invoke()

        // Then
        assertThat(result).isNull()
    }

    @Test
    fun `should return null when nutrition is null `() {
        // Given
        val mealWithNullNutrition = FakeData.ketoMealTest
        every { repository.getAllMeals() } returns listOf(mealWithNullNutrition[5])

        // When
        val result = ketoMealUseCase.invoke()

        // Then
        assertThat(result).isNull()
    }

    @Test
    fun `should suggest the same meal when it has null id`() {
        // Given
        val mealWithNullId = Meal(
            mealId = null,
            tags = listOf("dinner"),
            nutrition = Nutrition(carbohydrates = 5.0f, protein = 20.0f, totalFat = 15.0f)
        )
        every { repository.getAllMeals() } returns listOf(mealWithNullId)

        // When
        val first = ketoMealUseCase.invoke()
        val second = ketoMealUseCase.invoke()

        // Then
        assertThat(first).isEqualTo(second)
    }

    @Test
    fun `should  return meal if protein or fat in meal  does not  minimum keto rules`() {
        val testMeals = FakeData.testMealsOfMinProteinAndTotalFat

        every { repository.getAllMeals() } returns testMeals

        val result = ketoMealUseCase.invoke()

        assertThat(result).isEqualTo(testMeals[2])
    }
}