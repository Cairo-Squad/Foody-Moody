package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class SweetsNoEggsUseCaseTest {
	private lateinit var repository: MealRepository
	private lateinit var sweetsNoEggsUseCase: SweetsNoEggsUseCase
	
	@BeforeEach
	fun setup() {
		repository = mockk(relaxed = true)
		sweetsNoEggsUseCase = SweetsNoEggsUseCase(repository)
	}
	
	@Test
	fun `should return a sweet meal without eggs when available`() {
		// Given
		every { repository.getAllMeals() } returns getAllMeals()
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isIn(getAllMeals())
	}
	
	@Test
	fun `should return null when no sweet meals without eggs are available`() {
		// Given
		every { repository.getAllMeals() } returns listOf(
			getAllMeals()[0],
			getAllMeals()[2]
		)
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isNull()
	}
	
	
	@Test
	fun `should return null after all eligible sweets have been suggested`() {
		// Given
		every { repository.getAllMeals() } returns listOf(
			Meal(mealId = 1, tags = listOf("sweet", "dessert"), ingredients = listOf("sugar", "flour", "milk"))
		)
		
		// When
		sweetsNoEggsUseCase.invoke()
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isNull()
	}
	
	@Test
	fun `should identify meals with sweet tag regardless of case when available`() {
		// Given
		every { repository.getAllMeals() } returns listOf(getAllMeals()[6])
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isEqualTo(getAllMeals()[6])
	}
	
	@Test
	fun `should handle meals with null tags when available`() {
		// Given
		every { repository.getAllMeals() } returns listOf(getAllMeals()[4])
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isNull()
	}
	
	@Test
	fun `should return the meal if the tags has sweet with null ingredients`() {
		// Given
		val sweetMeal = getAllMeals()[7]
		every { repository.getAllMeals() } returns listOf(sweetMeal)
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isEqualTo(sweetMeal)
	}
	
	@Test
	fun `should return empty list when repository return empty list`() {
		// Given
		every { repository.getAllMeals() } returns emptyList()
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(result).isNull()
	}
	
	@Test
	fun `should result and second result be equal due to the meal id is null`() {
		// Given
		val sweetMealWithNullId =
			Meal(mealId = null, tags = listOf("sweet", "dessert"), ingredients = listOf("sugar", "flour", "milk"))
		every { repository.getAllMeals() } returns listOf(sweetMealWithNullId)
		
		// When
		val result = sweetsNoEggsUseCase.invoke()
		val secondResult = sweetsNoEggsUseCase.invoke()
		
		// Then
		assertThat(secondResult).isEqualTo(result)
	}
	
	
	private fun getAllMeals(): List<Meal> {
		return listOf(
			Meal(mealId = 5, tags = listOf("sweet", "dessert"), ingredients = listOf("sugar", "eggs", "flour")),
			Meal(mealId = 1, tags = listOf("sweet", "dessert"), ingredients = listOf("sugar", "flour", "milk")),
			Meal(mealId = 2, tags = listOf("savory", "dinner"), ingredients = listOf("chicken", "salt", "pepper")),
			Meal(mealId = 3, tags = listOf("sweet", "snack"), ingredients = listOf("chocolate", "flour", "milk")),
			Meal(mealId = 4, tags = null, ingredients = listOf("sugar", "flour", "milk")),
			Meal(mealId = null, tags = listOf("sweet", "dessert"), ingredients = listOf("sugar", "flour", "milk")),
			Meal(mealId = 6, tags = listOf("SwEeT", "dessert"), ingredients = listOf("sugar", "flour", "milk")),
			Meal(mealId = 1, tags = listOf("sweet", "dessert"), ingredients = null)
		
		)
	}
}