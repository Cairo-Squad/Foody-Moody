package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExploreOtherCountriesFoodCultureUseCaseTest {
	private lateinit var repository: MealRepository
	private lateinit var exploreOtherCountriesFoodCultureUseCase: ExploreOtherCountriesFoodCultureUseCase

	@BeforeEach
	fun setup() {
		repository = mockk(relaxed = true)
		exploreOtherCountriesFoodCultureUseCase = ExploreOtherCountriesFoodCultureUseCase(repository)
	}

	@Test
	fun `should return the list of meals by country name when enter a valid country name`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("asian", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(meals)
	}

	@Test
	fun `should return an empty list when enter a country name that is not exist`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("asian", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("italian")

		// Then
		assertThat(result).isEqualTo(emptyList<String>())
	}

	@Test
	fun `should return a valid list when enter a country name but in different case (upper - lower)`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("AMERICAN", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("asian", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(meals)
	}

	@Test
	fun `should return a valid list when enter a country name with white spaces`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("asian", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry(" american ")

		// Then
		assertThat(result).containsAnyIn(meals)
	}

	@Test
	fun `should skip the empty tags when enter a valid country name`() {
		// Given
		val meals = listOf(
			Meal(tags = null),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(meals)
	}

	@Test
	fun `should return exactly 20 meals if the length is bigger than 20 when enter a valid country name`(){
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result.size).isEqualTo(20)
	}

	@Test
	fun `should return all the list when enter less than 20 meals`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result.size).isEqualTo(15)
	}

	@Test
	fun `the list of meals should be shuffled when enter a valid country name`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "30-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "90-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "120-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "130-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "140-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "150-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "160-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "170-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "180-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "190-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "200-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "210-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "220-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "230-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result[0]).isNotEqualTo(meals[0])
	}

	@Test
	fun `should return an empty list when enter an empty country name`() {
		// Given
		val meals = listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
		)
		every { repository.getAllMeals() } returns meals

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("")

		// Then
		assertThat(result).isEmpty()
	}

	@Test
	fun `should handle empty response from repository when getting meals by country`() {
		// Given
		every { repository.getAllMeals() } returns emptyList()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).isEmpty()
	}
}