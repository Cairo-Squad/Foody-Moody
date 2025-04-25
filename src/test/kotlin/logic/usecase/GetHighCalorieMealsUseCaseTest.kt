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
	fun `should ignore low quality meals when meals missing name`() {
		// Given
		every { mealRepository.getAllMeals() } returns nonValidHighCalorieMeals() + highCalorieMeals()
		
		// When
		val result = getHighCalorieMealsUseCase.getHighCalorieMeals()
		
		// Then
		assertThat(result).hasSize(highCalorieMeals().size)
	}
	
	@Test
	fun `should ignore not high calorie meals when meals missing nutrition and calorie info`() {
		// Given
		every { mealRepository.getAllMeals() } returns nonValidHighCalorieMeals() + highCalorieMeals()
		
		// When
		val result = getHighCalorieMealsUseCase.getHighCalorieMeals()
		
		// Then
		assertThat(result).hasSize(highCalorieMeals().size)
	}
	
	@Test
	fun `should ignore low calories meals when list contains mixed calorie meals`() {
		// Given
		every { mealRepository.getAllMeals() } returns nonValidHighCalorieMeals() + highCalorieMeals()
		
		// When
		val result = getHighCalorieMealsUseCase.getHighCalorieMeals()
		
		// Then
		assertThat(result).hasSize(highCalorieMeals().size)
	}
	
	@Test
	fun `should return empty list when no high calorie meals are found`() {
		// Given
		every { mealRepository.getAllMeals() } returns nonValidHighCalorieMeals()
		
		// When
		val result = getHighCalorieMealsUseCase.getHighCalorieMeals()
		
		// Then
		assertThat(result).isEmpty()
	}
	
	private fun nonValidHighCalorieMeals() = listOf(
		Meal(mealName = null, nutrition = Nutrition(calories = 800f)),
		Meal(mealName = "Salad", nutrition = Nutrition(calories = null)),
		Meal(mealName = "Pizza", nutrition = Nutrition(calories = 400f))
	)
	
	private fun highCalorieMeals() = listOf(
		Meal(mealName = "Pizza", nutrition = Nutrition(calories = 800f)),
		Meal(mealName = "Burger", nutrition = Nutrition(calories = 900f)),
		Meal(mealName = "Salad", nutrition = Nutrition(calories = 850f)),
		Meal(mealName = "Sandwich", nutrition = Nutrition(calories = 780f)),
		Meal(mealName = "Noodles", nutrition = Nutrition(calories = 850f)),
		Meal(mealName = "Toast", nutrition = Nutrition(calories = 850f))
	)
}