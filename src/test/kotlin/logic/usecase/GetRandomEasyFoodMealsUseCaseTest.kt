package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.errors.NoSuchElementException
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
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
    fun `should throw EasyFoodMealsNotFoundException when mealRepository returns empty list`() {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        assertThrows<NoSuchElementException> {
            getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        }
    }

    @Test
    fun `should ignore low quality meals when name is missing`() {
        // Given
        every { mealRepository.getAllMeals() } returns nonValidEasyFoodMeals() + lessThan10EasyFoodMeals()


        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result).hasSize(lessThan10EasyFoodMeals().size)
    }

    @Test
    fun `should ignore not easy food meals when missing one requirement`() {
        // Given
        every { mealRepository.getAllMeals() } returns nonValidEasyFoodMeals() + lessThan10EasyFoodMeals()

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result).hasSize(lessThan10EasyFoodMeals().size)
    }

    @Test
    fun `should retrieve only easy food meals when filtering meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns nonValidEasyFoodMeals() + lessThan10EasyFoodMeals()

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result).hasSize(2)
    }

    @Test
    fun `getRandomEasyFoodMeals should return exactly 10 meals when more than 10 matching meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns easyFoodMeals()

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result).hasSize(10)
    }

    @Test
    fun `getRandomEasyFoodMeals should return all matching meals when less than 10 matching meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns lessThan10EasyFoodMeals()

        // When
        val result = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(result).hasSize(lessThan10EasyFoodMeals().size)
    }

    @Test
    fun `getRandomEasyFoodMeals should throw EasyFoodMealsNotFoundException when no easy food meals exist`() {
        // Given
        every { mealRepository.getAllMeals() } returns nonValidEasyFoodMeals()

        // When & Then
        assertThrows<NoSuchElementException> {
            getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        }
    }

    @Test
    fun `getRandomEasyFoodMeals should return shuffled meals every time when called`() {
        // Given
        every { mealRepository.getAllMeals() } returns easyFoodMeals()

        // When
        val firstResult = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()
        val secondResult = getRandomEasyFoodMealsUseCase.getRandomEasyFoodMeals()

        // Then
        assertThat(firstResult).isNotEqualTo(secondResult)
    }

    private fun easyFoodMeals(): List<Meal> = listOf(
        Meal(mealName = "Salad", minutes = 10, numberOfIngredients = 2, numberOfSteps = 2),
        Meal(mealName = "Smoothie", minutes = 5, numberOfIngredients = 4, numberOfSteps = 1),
        Meal(mealName = "Egg Salad", minutes = 10, numberOfIngredients = 3, numberOfSteps = 2),
        Meal(mealName = "Yogurt Parfait", minutes = 5, numberOfIngredients = 3, numberOfSteps = 2),
        Meal(mealName = "Pasta", minutes = 15, numberOfIngredients = 3, numberOfSteps = 3),
        Meal(mealName = "Rice and Beans", minutes = 15, numberOfIngredients = 3, numberOfSteps = 3),
        Meal(mealName = "Salad", minutes = 10, numberOfIngredients = 10, numberOfSteps = 2),
        Meal(mealName = "Salad", minutes = 10, numberOfIngredients = 2, numberOfSteps = 2),
        Meal(mealName = "Smoothie", minutes = 5, numberOfIngredients = 4, numberOfSteps = 1),
        Meal(mealName = "Egg Salad", minutes = 10, numberOfIngredients = 3, numberOfSteps = 2),
        Meal(mealName = "Yogurt Parfait", minutes = 5, numberOfIngredients = 3, numberOfSteps = 2),
        Meal(mealName = "Pasta", minutes = 15, numberOfIngredients = 3, numberOfSteps = 3),
    )

    private fun lessThan10EasyFoodMeals() = listOf(
        Meal(mealName = "Smoothie", minutes = 5, numberOfIngredients = 4, numberOfSteps = 1),
        Meal(mealName = "Egg Salad", minutes = 10, numberOfIngredients = 3, numberOfSteps = 2),
    )

    private fun nonValidEasyFoodMeals() = listOf(
        Meal(mealName = null, minutes = 20, numberOfIngredients = 5, numberOfSteps = 5),
        Meal(mealName = "Pizza", minutes = null, numberOfIngredients = 5, numberOfSteps = 5),
        Meal(mealName = "Pizza", minutes = 20, numberOfIngredients = null, numberOfSteps = 5),
        Meal(mealName = "Pizza", minutes = 20, numberOfIngredients = 5, numberOfSteps = null),
        Meal(mealName = "Pizza", minutes = 35, numberOfIngredients = 4, numberOfSteps = 4),
        Meal(mealName = "Pizza", minutes = 20, numberOfIngredients = 8, numberOfSteps = 4),
        Meal(mealName = "Pizza", minutes = 20, numberOfIngredients = 4, numberOfSteps = 8)
    )
}