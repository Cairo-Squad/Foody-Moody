package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import logic.model.Nutrition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SuggestMealsToGymUseCaseTest {

    private val mealsRepository: MealRepository = mockk(relaxed = true)
    private lateinit var suggestMealsToGymUseCase: SuggestMealsToGymUseCase

    @BeforeEach
    fun setup() {
        suggestMealsToGymUseCase = SuggestMealsToGymUseCase(mealsRepository)
    }

    @Test
    fun `Given inValid calories value and valid protein value,When validating,Then throws IllegalArgumentException`() {
        //Given
        val calories = -1f
        val protein = 15f
        //When

        //Then
        val illegalArgumentException = assertThrows<IllegalArgumentException> {
            suggestMealsToGymUseCase.getMatchedMeals(
                calories = calories, protein = protein
            )
        }
        assertEquals(expected = "Calories must be >= 0", actual = illegalArgumentException.message)

    }

    @Test
    fun `Given valid calories value and inValid protein value,When validating,Then throws IllegalArgumentException`() {
        //Given
        val calories = 400f
        val protein = -1f
        //When

        //Then
        val illegalArgumentException = assertThrows<IllegalArgumentException> {
            suggestMealsToGymUseCase.getMatchedMeals(
                calories = calories, protein = protein
            )
        }
        assertEquals(expected = "Protein must be >= 0", actual = illegalArgumentException.message)
    }

    @Test
    fun `Given a valid calories and protein values,When filtering meals with null values of calories,Then returns an empty list`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(protein = 10f, calories = null))
        )
        val calories = 400f
        val protein = 10f

        //When
        val result = suggestMealsToGymUseCase.getMatchedMeals(calories, protein)
        //Then
        assertThat(result).isEmpty()

    }

    @Test
    fun `Given a valid calories and protein values, When filtering meals with null values of protein,Then returns an empty list`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(calories = 400f, protein = null)),
        )
        val calories = 400f
        val protein = 10f

        //When
        val result = suggestMealsToGymUseCase.getMatchedMeals(calories, protein)
        //Then
        assertThat(result).isEmpty()

    }

    @Test
    fun `Given valid calories and protein values, When the given protein value doesn't match with any meal's protein value,Then returns emptyList`() {
        //Given
        val calories = 400f
        val protein = 10f
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(calories = 350f, protein = 4f)), //unMatched protein value
        )
        //When
        val result = suggestMealsToGymUseCase.getMatchedMeals(calories, protein)
        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Given valid calories and protein values, When the given calories value doesn't match with any meal's calories value,Then returns emptyList`() {
        //Given
        val calories = 400f
        val protein = 10f
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(calories = 300f, protein = 5f)), // unMatched calories value
        )
        //When
        val result = suggestMealsToGymUseCase.getMatchedMeals(calories, protein)
        //Then
        assertThat(result).isEmpty()
    }
}