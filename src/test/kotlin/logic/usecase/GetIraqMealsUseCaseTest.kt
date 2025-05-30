package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetIraqMealsUseCaseTest {
    private lateinit var getIraqMealsUseCase: GetIraqMealsUseCase
    private lateinit var repository: MealRepository

    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true)
        getIraqMealsUseCase = GetIraqMealsUseCase(repository)
    }

    @Test
    fun `should return valid list when GetIraqMeals function is called`() {
        //Given
        every { repository.getAllMeals() } returns ValidListOfMeal()

        //When
        val actualResult = getIraqMealsUseCase.getIraqMeals()

        //Then
        assertThat(actualResult).isNotNull()
    }

    @Test
    fun `should return null when no iraq meals found`() {
        //Given
        every { repository.getAllMeals() } returns ListOfNoIraqMeals()

        //When
        val actualResult = getIraqMealsUseCase.getIraqMeals()

        //Then
        assertThat(actualResult).isNull()

    }

    @Test
    fun `should return null when meals list is empty`() {
        //Given
        every { repository.getAllMeals() } returns emptyList()
        //When
        val actualResult = getIraqMealsUseCase.getIraqMeals()

        //Then
        assertThat(actualResult).isNull()

    }

    @Test
    fun `should return valid list when only the description is valid`() {
        //Given
        every { repository.getAllMeals() } returns ListOfIraqMealsWithOnlyValidDescription()
        //When
        val actualResult = getIraqMealsUseCase.getIraqMeals()

        //Then
        assertThat(actualResult).isNotNull()
    }

    @Test
    fun `should return valid list when only the tags is valid`() {
        //Given
        every { repository.getAllMeals() } returns ListOfIraqMealsWithOnlyValidTags()
        //When
        val actualResult = getIraqMealsUseCase.getIraqMeals()

        //Then
        assertThat(actualResult).isNotNull()
    }

    private fun ValidListOfMeal() = listOf(
        Meal(
            mealName = "Masgouf",
            mealDescription = "A traditional Iraqi grilled.",
            tags = listOf("Iraq", "Grilled", "Seafood")
        ),
        Meal(
            mealName = "Kubba",
            mealDescription = "Popular Iraqi dumplings made",
            tags = listOf("Iraq", "Dumplings", "Comfort Food")
        ),
    )

    private fun ListOfNoIraqMeals() = listOf(
        Meal(
            mealName = "Masgouf",
            mealDescription = "A traditional bsbs grilled.",
            tags = listOf("bsbs", "Grilled", "Seafood")
        ),
        Meal(
            mealName = "Kubba",
            mealDescription = "Popular bsbs dumplings made",
            tags = listOf("bsbs", "Dumplings", "Comfort Food")
        ),
    )

    private fun ListOfIraqMealsWithOnlyValidDescription() = listOf(
        Meal(
            mealName = "Masgouf",
            mealDescription = "A traditional Iraqi grilled.",
            tags = listOf("bsbs", "Grilled", "Seafood")
        ),
        Meal(
            mealName = "Kubba",
            mealDescription = "Popular Iraqi dumplings made",
            tags = listOf("bsbs", "Dumplings", "Comfort Food")
        ),
    )

    private fun ListOfIraqMealsWithOnlyValidTags() = listOf(
        Meal(
            mealName = "Masgouf",
            mealDescription = "A traditional bsbs grilled.",
            tags = listOf("iraq", "Grilled", "Seafood")
        ),
        Meal(
            mealName = "Kubba",
            mealDescription = "Popular bsbs dumplings made",
            tags = listOf("bsbs", "Dumplings", "Comfort Food")
        ),
    )

}