package logic.usecase

import com.google.common.truth.Truth.assertThat
import data.FakeData
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import logic.model.Nutrition
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class GetSeafoodMealsSortedByProteinUseCaseTest {
    private lateinit var mealRepository : MealRepository
    private lateinit var getSeafoodMealsSortedByProteinUseCase : GetSeafoodMealsSortedByProteinUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk(relaxed = true)
        getSeafoodMealsSortedByProteinUseCase = GetSeafoodMealsSortedByProteinUseCase(mealRepository)
    }

    @Test
    fun `should return seafood meals sorted by protein descending when matching seafood with tags seafood `() {
        // Given
        val meals = FakeData.allMeals
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("carb" , "fish","Scallop","ScallopRed")
    }

    @Test
    fun `should return empty list when seafood meals not found`() {
        // Given
        val meals = listOf(FakeData.allMeals[4])
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return seafood meals sorted when  meals with null tags and match anther meal when `() {
        // Given
        val meals = FakeData.allMeals
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("carb","fish","Scallop","ScallopRed")
    }

    @Test
    fun `should set protein as zerdo when protein is null`() {
        // Given
        val mealsOfNullProtein = listOf(
            Meal(mealName = "FishRed" , mealId = 1 , tags = listOf("Seafood") , nutrition = Nutrition( protein =  50f )
            ) , Meal(mealName = "Fish" , mealId = 2 , tags = listOf("Seafood") , nutrition = Nutrition(protein = null),
            ) , Meal(mealName = "carb" , mealId = 3 , tags = listOf("Seafood") , nutrition = Nutrition(protein = 30f )
            )
        )
        every { mealRepository.getAllMeals() } returns mealsOfNullProtein

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("FishRed" , "carb" , "Fish")
    }

    @Test
    fun `should  set nutrition as zero when nutrition is null`() {
        // Given
       val meals = FakeData.allMeals
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("carb" , "fish" , "Scallop" ,"ScallopRed")
    }



}