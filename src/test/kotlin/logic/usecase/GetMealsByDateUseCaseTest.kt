package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.FakeData
import data.errors.NoResultException
import data.errors.ValidationException
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GetMealsByDateUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var getMealsByDateUseCase: GetMealsByDateUseCase

    @BeforeEach
    fun setup() {
        mealRepository = mockk(relaxed = true)
        getMealsByDateUseCase = GetMealsByDateUseCase(mealRepository)
    }

    @Test
    fun `should throw Exception when the repository throws an exception`() {
        // Given
        every { mealRepository.getAllMeals() } throws Exception()

        // When
        val result = getMealsByDateUseCase.getMealsByDate("2005-09-15")

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(Exception::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "gg56",
        "2020-gh-03.",
        "20 20- 10 -24"
    )
    fun `should throw ValidationException when given invalid date`(date: String) {
        // When
        val result = getMealsByDateUseCase.getMealsByDate(date)

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(ValidationException::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should throw NoResultException when the meals list is empty`(date: String) {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        // When
        val result = getMealsByDateUseCase.getMealsByDate(date)

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(NoResultException::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should throw NoResultException when the meals list doesn't contain any meal with the given date`(date: String) {
        // Given
        every { mealRepository.getAllMeals() } returns FakeData.allMeals

        // When
        val result = getMealsByDateUseCase.getMealsByDate(date)

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(NoResultException::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "2005-09-15",
        "2005-04-12"
    )
    fun `should return all meals with the given date when the meals list contain meals with that date`(date: String) {
        // Given
        every { mealRepository.getAllMeals() } returns FakeData.nonKetoMeals

        // When
        val result = getMealsByDateUseCase.getMealsByDate(date)

        // Then
        assertThat(result.getOrNull()).isNotEmpty()
    }

}