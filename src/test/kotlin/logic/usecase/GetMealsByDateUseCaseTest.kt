package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.FakeData
import data.errors.NoResultException
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class GetMealsByDateUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var getMealsByDateUseCase: GetMealsByDateUseCase

    @BeforeEach
    fun setup() {
        mealRepository = mockk(relaxed = true)
        getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository)
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should throw NoResultException when the meals list is empty`(dateString: String) {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        // When & Then
        assertThrows<NoResultException> {
            getMealsByDateUseCase.getMealsByDate(LocalDate.parse(dateString))
        }
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should throw NoResultException when the meals list doesn't contain any meal with the given date`(dateString: String) {
        // Given
        every { mealRepository.getAllMeals() } returns FakeData.allMeals

        // When & Then
        assertThrows<NoResultException> {
            getMealsByDateUseCase.getMealsByDate(LocalDate.parse(dateString))
        }
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should return all meals with the given date when the meals list contain meals with that date`(dateString: String) {
        // Given
        every { mealRepository.getAllMeals() } returns FakeData.nonKetoMeals

        // When
        val result = getMealsByDateUseCase.getMealsByDate(LocalDate.parse(dateString))

        // Then
        assertThat(result).isNotEmpty()
    }

}