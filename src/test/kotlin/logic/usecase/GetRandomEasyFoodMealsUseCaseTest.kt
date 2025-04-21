package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.errors.EasyFoodMealsNotFoundException
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.util.createMeal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetRandomEasyFoodMealsUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var getRandomEasyFoodMealsUseCase: GetRandomEasyFoodMealsUseCase

    @BeforeEach
    fun setup() {
        mealRepository = mockk(relaxed = true)
        getRandomEasyFoodMealsUseCase = GetRandomEasyFoodMealsUseCase(mealRepository)
    }

    @Test
    fun `getRandomEasyFoodMeals should throw EasyFoodMealsNotFoundException when mealRepository returns empty list`() {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        assertThrows<EasyFoodMealsNotFoundException> {
            getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        }
    }

    @Test
    fun `getRandomEasyFoodMeals should ignore low quality meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal(null, 20, 5, 5),
            createMeal("pizza", null, 5, 5),
            createMeal("pizza", 20, null, 5),
            createMeal("pizza", 20, 5, null),
            createMeal("pizza", 20, 5, 5),
        )

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun `getRandomEasyFoodMeals should ignore not easy food meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal("pizza", 35, 5, 5),
            createMeal("pizza", 20, 7, 5),
            createMeal("pizza", 20, 5, 7),
            createMeal("pizza", 20, 5, 5),
        )

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun `getRandomEasyFoodMeals should return exactly 10 meals when more than 10 matching meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal("Pasta", 15, 3, 3),
            createMeal("Salad", 10, 2, 2),
            createMeal("Sandwich", 5, 2, 1),
            createMeal("Scrambled Eggs", 7, 3, 2),
            createMeal("Instant Noodles", 3, 2, 2),
            createMeal("Toast", 5, 2, 1),
            createMeal("Fruit Salad", 5, 3, 2),
            createMeal("Grilled Cheese", 10, 3, 2),
            createMeal("Smoothie", 5, 4, 1),
            createMeal("Egg Salad", 10, 3, 2),
            createMeal("Yogurt Parfait", 5, 3, 2),
            createMeal("Rice and Beans", 15, 3, 3),
        )

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result.size).isEqualTo(10)
    }

    @Test
    fun `getRandomEasyFoodMeals should return all matching meals when less than 10 matching meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal("pizza", 20, 4, 4),
            createMeal("Pasta", 15, 3, 3),
            createMeal("Salad", 10, 2, 2)
        )

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun `getRandomEasyFoodMeals should throw EasyFoodMealsNotFoundException when no easy food meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf( createMeal("pizza", 40, 4, 4) )

        // When & Then
        assertThrows<EasyFoodMealsNotFoundException> {
            getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        }
    }

    @Test
    fun `getRandomEasyFoodMeals should return shuffled meals every time when called`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            createMeal("Pasta", 15, 3, 3),
            createMeal("Salad", 10, 2, 2),
            createMeal("Sandwich", 5, 2, 1),
            createMeal("Scrambled Eggs", 7, 3, 2),
            createMeal("Instant Noodles", 3, 2, 2),
            createMeal("Toast", 5, 2, 1),
            createMeal("Fruit Salad", 5, 3, 2),
            createMeal("Grilled Cheese", 10, 3, 2),
            createMeal("Smoothie", 5, 4, 1),
            createMeal("Egg Salad", 10, 3, 2),
            createMeal("Yogurt Parfait", 5, 3, 2),
            createMeal("Rice and Beans", 15, 3, 3),
            createMeal("Omelette", 10, 4, 2),
            createMeal("Avocado Toast", 5, 2, 2)
        )

        // When
        val firstResult = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        val secondResult = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(firstResult).isNotEqualTo(secondResult)
    }
}