package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.LogicConstants.FOR_LARGE_GROUP
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetMealsForLargeGroupUseCaseTest {

    private lateinit var repository: MealRepository
    private lateinit var getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true)
        getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(repository)
    }

    @Test
    fun `getAllMealsForLargeGroup should return a list of Italian Meals for large groups, when called`() {
        // Given
        every { repository.getAllMeals() } returns listOfMeals(
            Meal(
                mealName = "italian pasta",
                tags = listOf(FOR_LARGE_GROUP),
            )
        )

        // When
        val result = getMealsForLargeGroupUseCase.getAllMealsForLargeGroup()

        // Then
        assertThat(result).containsExactly(
            Meal(
                mealName = "italian pasta",
                tags = listOf(FOR_LARGE_GROUP),
            ),
        )
    }

    @Test
    fun `getAllMealsForLargeGroup should return empty list, when name is null`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(
                mealName = null,
                tags = listOf(FOR_LARGE_GROUP),
            )
        )

        // When
        val result = getMealsForLargeGroupUseCase.getAllMealsForLargeGroup()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `getAllMealsForLargeGroup should return empty list, when tags is null`() {
        // Given
        every { repository.getAllMeals() } returns listOfMeals()

        // When
        val result = getMealsForLargeGroupUseCase.getAllMealsForLargeGroup()

        // Then
        assertThat(result).isEmpty()
    }


    @Test
    fun `getAllMealsForLargeGroup should return empty list, when there are no Italian meals for large groups`() {
        // Given
        every { repository.getAllMeals() } returns listOfMeals(
            Meal(
                mealName = "saudi pasta",
                tags = listOf(FOR_LARGE_GROUP),
            )
        )

        // When
        val result = getMealsForLargeGroupUseCase.getAllMealsForLargeGroup()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `getAllMealsForLargeGroup should return a list of Italian Meals for large groups, when tags correct with whitespace`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(
                mealName = "italian pasta",
                tags = listOf(" $FOR_LARGE_GROUP  "),
            ),
        )

        // When
        val result = getMealsForLargeGroupUseCase.getAllMealsForLargeGroup()

        // Then
        assertThat(result).hasSize(1)
    }
}