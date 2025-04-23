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
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(getMultiNationalMeals())
	}

	@Test
	fun `should return an empty list when enter a country name that is not exist`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("italian")

		// Then
		assertThat(result).isEqualTo(emptyList<String>())
	}

	@Test
	fun `should return a valid list when enter a country name but in different case (upper - lower)`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(getMultiNationalMeals())
	}

	@Test
	fun `should return a valid list when enter a country name with white spaces`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry(" american ")

		// Then
		assertThat(result).containsAnyIn(getMultiNationalMeals())
	}

	@Test
	fun `should skip the empty tags when enter a valid country name`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result).containsAnyIn(getMultiNationalMeals())
	}

	@Test
	fun `should return exactly 20 meals if the length is bigger than 20 when enter a valid country name`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result.size).isEqualTo(20)
	}

	@Test
	fun `the list of meals should be shuffled when enter a valid country name`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

		// When
		val result = exploreOtherCountriesFoodCultureUseCase.getTwentyRandomMealByCountry("american")

		// Then
		assertThat(result[0]).isNotEqualTo(getMultiNationalMeals()[0])
	}

	@Test
	fun `should return an empty list when enter an empty country name`() {
		// Given
		every { repository.getAllMeals() } returns getMultiNationalMeals()

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


	private fun getMultiNationalMeals(): List<Meal> {
		return listOf(
			Meal(tags = listOf("american", "60-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("russian", "70-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("greece", "80-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = null),
			Meal(tags = listOf("AMERICAN", "100-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "110-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf(" american ", "120-minutes-or-less", "time-to-make", "cuisine")),
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
			Meal(tags = listOf("american", "240-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "250-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "260-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "270-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "280-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "290-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "300-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "310-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "320-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "330-minutes-or-less", "time-to-make", "cuisine")),
			Meal(tags = listOf("american", "340-minutes-or-less", "time-to-make", "cuisine")),
		)
	}
}