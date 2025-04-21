package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class GetSeafoodMealsSortedByProteinUseCaseTest {
    private lateinit var mealRepository: MealRepository
    private lateinit var getSeafoodMealsSortedByProteinUseCase: GetSeafoodMealsSortedByProteinUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk(relaxed = true)
        getSeafoodMealsSortedByProteinUseCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository)
    }

    @Test
    fun `should return seafood meals sorted by protein descending`() {
        // Given
        val meals = listOf(
            createMealOfSeafood(
                mealName = "fish",
                mealId = 1,
                tags = listOf("Seafood"),
                protein = 20f
            ),
            createMealOfSeafood(
                mealName = "carb",
                mealId = 2,
                tags = listOf("seafood"),
                protein = 25f
            ),
            createMealOfSeafood(
                mealName = "meet",
                mealId = 3,
                tags = listOf("Vegetarian"),
                protein = 20f
            ),
            createMealOfSeafood(
                mealName = "egg",
                mealId = 5,
                tags = listOf("Meat"),
                protein = 35f
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName })
            .containsExactly("carb", "fish")
    }

    @Test
    fun `should return empty list when seafood meals nott found`() {
        // Given
        val meals = listOf(
            createMealOfSeafood(
                mealName = "Fish",
                mealId = 1,
                tags = listOf("sweet"),
                protein = 20f
            ),
            createMealOfSeafood(
                mealName = "FishRed",
                mealId = 2,
                tags = listOf("Meat"),
                protein = 30f
            ),
            createMealOfSeafood(
                mealName = "Chicken",
                mealId = 3,
                tags = listOf("Meat"),
                protein = 25f
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result)
            .isEmpty()
    }

    @Test
    fun `should handle meals with null tags`() {
        // Given
        val meals = listOf(
            createMealOfSeafood(
                mealName = "Fish",
                mealId = 6,
                tags = null,
                protein = 65f
            ),
            createMealOfSeafood(
                mealName = "carb",
                mealId = 7,
                tags = listOf("Seafood"),
                protein = 40f
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName })
            .containsExactly("carb")
    }

    @Test
    fun `should treat protein as 0 when protein is null`() {
        // Given
        val meals = listOf(
            createMealOfSeafood(
                mealName = "FishRed",
                mealId = 1,
                tags = listOf("Seafood"),
                protein = 50f
            ),
            createMealOfSeafood(
                mealName = "Fish",
                mealId = 2,
                tags = listOf("Seafood"),
                protein = null
            ),
            createMealOfSeafood(
                mealName = "carb",
                mealId = 3,
                tags = listOf("Seafood"),
                protein = 30f
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName })
            .containsExactly("Fishred", "carb", "Fish")
    }

    @Test
    fun `should sort seafood meals when some have null nutrition`() {
        // Given
        val meals = listOf(
            createMealOfSeafood(
                mealName = "FishRed",
                mealId = 1,
                tags = listOf("Seafood"),
                protein = 50f
            ),
            createMealOfSeafood(
                mealName = "carb",
                mealId = 2,
                tags = listOf("Seafood"),
                protein = null
            ),
            createMealOfSeafood(
                mealName = "fish",
                mealId = 3,
                tags = listOf("Seafood"),
                protein = null
            ).copy(nutrition = null),
            createMealOfSeafood(
                mealName = "carbRed",
                mealId = 4,
                tags = listOf("Seafood"),
                protein = 30f
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).
        containsExactly("FishRed", "carbRed", "carb", "fish")
    }
}
