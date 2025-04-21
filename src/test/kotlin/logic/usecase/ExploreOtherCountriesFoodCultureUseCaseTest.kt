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
}