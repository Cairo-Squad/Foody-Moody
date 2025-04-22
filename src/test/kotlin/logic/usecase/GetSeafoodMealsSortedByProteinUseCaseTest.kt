package logic.usecase

import com.google.common.truth.Truth.assertThat
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
        val meals = listOf(
            Meal(
                mealName = "fish" ,
                mealId = 1 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , 20f , null , null)
            ) , Meal(
                mealName = "carb" ,
                mealId = 2 ,
                tags = listOf("seafood") ,
                nutrition = Nutrition(null , null , null , null , 25f , null , null)
            ) , Meal(
                mealName = "meet" ,
                mealId = 3 ,
                tags = listOf("Vegetarian") ,
                nutrition = Nutrition(null , null , null , null , 20f , null , null)
            ) , Meal(
                mealName = "egg" ,
                mealId = 5 ,
                tags = listOf("Meat") ,
                nutrition = Nutrition(null , null , null , null , 35f , null , null)
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("carb" , "fish")
    }

    @Test
    fun `should return empty list when seafood meals not found`() {
        // Given
        val meals = listOf(
            Meal(
                mealName = "Fish" ,
                mealId = 1 ,
                tags = listOf("sweet") ,
                nutrition = Nutrition(null , null , null , null , 20f , null , null)
            ) , Meal(
                mealName = "FishRed" ,
                mealId = 2 ,
                tags = listOf("Meat") ,
                nutrition = Nutrition(null , null , null , null , 30f , null , null)
            ) , Meal(
                mealName = "Chicken" ,
                mealId = 3 ,
                tags = listOf("Meat") ,
                nutrition = Nutrition(null , null , null , null , 25f , null , null)
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return seafood meals sorted when  meals with null tags and match anther meal when `() {
        // Given
        val meals = listOf(
            Meal(
                mealName = "Fish" ,
                mealId = 6 ,
                tags = null ,
                nutrition = Nutrition(null , null , null , null , 65f , null , null)
            ) , Meal(
                mealName = "carb" ,
                mealId = 7 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , 40f , null , null)
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("carb")
    }

    @Test
    fun `should set protein as zero when protein is null`() {
        // Given
        val meals = listOf(
            Meal(
                mealName = "FishRed" ,
                mealId = 1 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , 50f , null , null)
            ) , Meal(
                mealName = "Fish" ,
                mealId = 2 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , null , null , null)
            ) , Meal(
                mealName = "carb" ,
                mealId = 3 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , 30f , null , null)
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("FishRed" , "carb" , "Fish")
    }

    @Test
    fun `should  set nutrition as zero when nutrition is null`() {
        // Given
        val meals = listOf(
            Meal(
                mealName = "FishRed" ,
                mealId = 1 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , 10f , null , null , 50f , null , null)
            ) , Meal(
                mealName = "carb" ,
                mealId = 2 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , 40f , null , null , null , null , null)
            ) , Meal(
                mealName = "fish" , mealId = 3 , tags = listOf("Seafood") , nutrition = null
            ) , Meal(
                mealName = "carbRed" ,
                mealId = 4 ,
                tags = listOf("Seafood") ,
                nutrition = Nutrition(null , null , null , null , 30f , null , null)
            )
        )
        every { mealRepository.getAllMeals() } returns meals

        // When
        val result = getSeafoodMealsSortedByProteinUseCase.getSeafoodMealsSortedByProtein()

        // Then
        assertThat(result.map { it.mealName }).containsExactly("FishRed" , "carbRed" , "carb" , "fish")
    }
}