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
            suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(
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
            suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(
                calories = calories, protein = protein
            )
        }
        assertEquals(expected = "Protein must be >= 0", actual = illegalArgumentException.message)
    }

    @Test
    fun `Given a valid calories and protein values,When filtering meals with null values of calories,Then returns an empty list`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(Meal(nutrition = Nutrition(protein = 10f)), Meal())
        val calories = 400f
        val protein = 10f

        //When
        val result = suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(calories, protein)
        //Then
        assertThat(result).isEmpty()

    }

    @Test
    fun `Given a valid calories and protein values, When filtering meals with null values of protein,Then returns an empty list`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(Meal(nutrition = Nutrition(calories = 400f)), Meal())
        val calories = 400f
        val protein = 10f

        //When
        val result = suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(calories, protein)
        //Then
        assertThat(result).isEmpty()

    }

    @Test
    fun `Given matched calories and unMatched protein values, When filtering meals and no meals were matched,Then returns emptyList`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(calories = 350f, protein = 4f)),
        )
        val calories = 400f
        val protein = 10f
        //When
        val result = suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(calories, protein)
        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Given unMatched calories and matched protein values, When filtering meals and no meals were matched,Then returns emptyList`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            Meal(nutrition = Nutrition(calories = 300f, protein = 5f)),
        )
        val calories = 400f
        val protein = 10f
        //When
        val result = suggestMealsToGymUseCase.getMealsBasedOnCaloriesAndProtein(calories, protein)
        //Thena
        assertThat(result).isEmpty()
    }


}