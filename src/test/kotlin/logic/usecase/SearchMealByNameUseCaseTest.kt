package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SearchMealByNameUseCaseTest {

    lateinit var repository: MealRepository
    lateinit var searchMealByNameUseCase: SearchMealByNameUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true)
        searchMealByNameUseCase = SearchMealByNameUseCase(repository)
    }

    @Test
    fun `should return filtered meals when search input matches a meal name substring`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "2 in 1 egyptian meal"),
            Meal(mealName = "2 in 1 italian meal"),
            Meal(mealName = "2 in 1 saudi meal"),
            Meal(mealName = "2 in 1 hindi meal")
        )
        val searchInput = "italian"

        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return empty list when search input doesn't matches any meal name substring`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "2 in 1 egyptian meal"),
            Meal(mealName = "2 in 1 american meal"),
            Meal(mealName = "2 in 1 saudi meal"),
            Meal(mealName = "2 in 1 hindi meal")
        )
        val searchInput = "italian"

        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(strings = ["italian", "ITALIAN", "ItaLian", "ITAlian"])
    fun `should return filtered meals case-insensitive when search input matches a meal name`(searchInput: String) {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "2 in 1 egyptian meal"),
            Meal(mealName = "2 in 1 italian meal"),
            Meal(mealName = "2 in 1 saudi meal"),
            Meal(mealName = "2 in 1 hindi meal")
        )
        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return filtered meals regardless whitespaces when search input matches a meal name substring`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "2 in 1 egyptian meal"),
            Meal(mealName = "2 in 1 italian meal"),
            Meal(mealName = "2 in 1 saudi meal"),
            Meal(mealName = "2 in 1 hindi meal")
        )
        val searchInput = " italian "

        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return empty list, when typo exceeds tolerance`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "Italian Pizza"),
            Meal(mealName = "Greek Salad")
        )
        val searchInput = "Italiano"

        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return case-insensitive fuzzy matches, when typo within tolerance`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "Italian Pizza"),
            Meal(mealName = "Spicy Thai Curry")
        )
        val searchInput = "Thay"

        // When
        val result = searchMealByNameUseCase.searchMealByName(searchInput)

        // Then
        assertThat(result).containsExactly(
            Meal(mealName = "Spicy Thai Curry"),
        )
    }

}