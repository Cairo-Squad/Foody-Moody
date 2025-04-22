package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomMealUseCaseTest {
    private lateinit var repository: MealRepository
    lateinit var getRandomMealUseCase: GetRandomMealUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true)
        getRandomMealUseCase = GetRandomMealUseCase(repository)
    }

    @Test
    fun `should return a meal when there is a meal valid`() {
        //Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "Burger", minutes = 10),
            Meal(mealName = "Pizza", minutes = 20),
            Meal(mealName = "Pasta", minutes = 15)
        )
        //When
        val actualResult = getRandomMealUseCase.getRandomMeal()
        //Then
        assertThat(actualResult).isNotNull()
    }
    @Test
    fun `should return null when meal list is empty`() {
        // Given
        every { repository.getAllMeals() } returns emptyList()

        // When
        val actualResult = getRandomMealUseCase.getRandomMeal()

        // Then
        assertThat(actualResult).isNull()
    }
    @Test
    fun `should return null when all meals have null names `() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName =null , minutes = 30),
            Meal(mealName =null , minutes = 20),
            Meal(mealName =null , minutes = 30)
        )
        // When

        val actualResult = getRandomMealUseCase.getRandomMeal()

        // Then
        assertThat(actualResult).isNull()
    }
    @Test
    fun `should return null when all meals have null minutes `() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName ="meat" , minutes = null),
            Meal(mealName ="koko" , minutes = null),
            Meal(mealName ="bata" , minutes = null)
        )
        // When

        val actualResult = getRandomMealUseCase.getRandomMeal()

        // Then
        assertThat(actualResult).isNull()
    }
    @Test
    fun `should return a meal when there is a null unimportant attribute`() {
        //Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "Burger", minutes = 10 , numberOfSteps = null),
            Meal(mealName = "Pizza", minutes = 20, numberOfSteps = null),
            Meal(mealName = "Pasta", minutes = 15, numberOfSteps = null)
        )
        //When
        val actualResult = getRandomMealUseCase.getRandomMeal()
        //Then
        assertThat(actualResult).isNotNull()
    }
    @Test
    fun `should call getAllMeals exactly once`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            Meal(mealName = "Burger", minutes = 10)
        )
        // When

        getRandomMealUseCase.getRandomMeal()
        // Then

        io.mockk.verify(exactly = 1) { repository.getAllMeals() }
    }




}
