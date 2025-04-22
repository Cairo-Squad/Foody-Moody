package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import logic.model.Nutrition
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetHighCalorieMealsUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var getHighCalorieMealsUseCase: GetHighCalorieMealsUseCase

    @BeforeEach
    fun setup() {
        mealRepository = mockk(relaxed = true)
        getHighCalorieMealsUseCase = GetHighCalorieMealsUseCase(mealRepository)
    }

    @Test
    fun `should return empty list when mealRepository return empty list`() {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        // When
        val result = getHighCalorieMealsUseCase.getHighCalorieMeals()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should ignore low quality meals when list has meals missing name or calorie info`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            Meal(mealName = null, nutrition = Nutrition(calories = 800f)),
            Meal(mealName = "Pizza", nutrition =  null),
            Meal(mealName = "Salad", nutrition = Nutrition(calories = null)),
            Meal(mealName = "Burger", nutrition = Nutrition(calories = 800f))
        )

        // When
        val result = getHighCalorieMealsUseCase.getHighCalorieMeals()

        // Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should ignore low calories meals when list contains mixed calorie meals`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            Meal(mealName = "Pizza", nutrition = Nutrition(calories = 500f)),
            Meal(mealName = "Burger", nutrition = Nutrition(calories = 600f)),
            Meal(mealName = "Salad", nutrition = Nutrition(calories = 800f)),
            Meal(mealName = "Sandwich", nutrition = Nutrition(calories = 900f))
        )

        // When
        val result = getHighCalorieMealsUseCase.getHighCalorieMeals()

        // Then
        assertThat(result).hasSize(2)
    }

    @Test
    fun `should return empty list when no high calorie meals are found`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            Meal(mealName = "Pizza", nutrition = Nutrition(calories = 400f)),
            Meal(mealName = "Burger", nutrition = Nutrition(calories = 300f)),
            Meal(mealName = "Salad", nutrition = Nutrition(calories = 500f)),
            Meal(mealName = "Sandwich", nutrition = Nutrition(calories = 600f))
        )

        // When
        val result = getHighCalorieMealsUseCase.getHighCalorieMeals()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return different list when called more than once`() {
        // Given
        every { mealRepository.getAllMeals() } returns listOf(
            Meal(mealName = "Pizza", nutrition = Nutrition(calories = 800f)),
            Meal(mealName = "Burger", nutrition = Nutrition(calories = 900f)),
            Meal(mealName = "Salad", nutrition = Nutrition(calories = 850f)),
            Meal(mealName = "Sandwich", nutrition = Nutrition(calories = 780f)),
            Meal(mealName = "Noodles", nutrition = Nutrition(calories = 850f)),
            Meal(mealName = "Toast", nutrition = Nutrition(calories = 850f)),
            Meal(mealName = "Fruit Salad", nutrition = Nutrition(calories = 850f)),
            Meal(mealName = "Grilled Cheese", nutrition = Nutrition(calories = 850f)),
            Meal(mealName = "Smoothie", nutrition = Nutrition(calories = 850f)),
        )

        // When
        val firstCallResult = getHighCalorieMealsUseCase.getHighCalorieMeals()
        val secondCallResult = getHighCalorieMealsUseCase.getHighCalorieMeals()

        // Then
        assertThat(firstCallResult).isNotEqualTo(secondCallResult)
    }

}