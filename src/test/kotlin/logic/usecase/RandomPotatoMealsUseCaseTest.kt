package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RandomPotatoMealsUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var randomPotatoMealsUseCase: RandomPotatoMealsUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk(relaxed = true)
        randomPotatoMealsUseCase = RandomPotatoMealsUseCase(mealRepository)
    }

    @Test
    fun `should return empty list when meals list is empty`() {
        // Given
        every { mealRepository.getAllMeals() } returns emptyList()

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).isEmpty()
    }

    @Test
    fun `should return empty list when no meal has ingredients in the meals list`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "cheese cake", ingredients = null),
            Meal(mealName = "mac and cheese", ingredients = null)
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).isEmpty()
    }

    @Test
    fun `should return empty list when no potato meals in the meals list`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "cheese cake", ingredients = listOf("flour", "sugar", "milk")),
            Meal(mealName = "mac and cheese", ingredients = listOf("macaroni", "cheese", "milk"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).isEmpty()
    }

    @Test
    fun `should return all potato meals when there is less than 10 potato meals in the meals list`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "alouette  potatoes", ingredients = listOf("shallots", "parsley", "new potatoes")),
            Meal(mealName = "cream  of cauliflower soup  vegan", ingredients = listOf("potatoes", "garlic", "onion"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).hasSize(2)
    }

    @Test
    fun `should return exactly 10 potato meals if the meals list has more than 10 potato meals`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "greek potatoes  plaki", ingredients = listOf("potatoes", "onion", "canola oil")),
            Meal(mealName = "cream  of cauliflower soup  vegan", ingredients = listOf("potatoes", "garlic", "onion")),
            Meal(
                mealName = "oven potatoes tray  saniat patates ba el lahma fi al forn",
                ingredients = listOf("potatoes", "onions", "butter")
            ),
            Meal(mealName = "easy baked potato soup", ingredients = listOf("potatoes", "garlic", "onion")),
            Meal(mealName = "pan fried turnips and potatoes", ingredients = listOf("potatoes", "turnips", "butter")),
            Meal(mealName = "sheri s slow cooker beef stew", ingredients = listOf("potatoes", "carrot", "flour")),
            Meal(mealName = "papa s potato   ham soup", ingredients = listOf("potatoes", "cooked ham", "water")),
            Meal(
                mealName = "creamy parmesan mashed potatoes",
                ingredients = listOf("potatoes", "red potatoes", "unsalted butter")
            ),
            Meal(mealName = "secret weapon potato salad", ingredients = listOf("potatoes", "salt", "dill pickles")),
            Meal(mealName = "potato logs", ingredients = listOf("potatoes", "margarine", "salt")),
            Meal(mealName = "swedish potato salad", ingredients = listOf("potatoes", "cooked beets", "capers"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).hasSize(10)
    }

    @Test
    fun `should return only meals that contains potatoes in their ingredients from the meals list`() {
        // Given
        val firstPotatoMeal =
            Meal(mealName = "greek potatoes  plaki", ingredients = listOf("potatoes", "onion", "canola oil"))
        val secondPotatoMeal =
            Meal(mealName = "cream  of cauliflower soup  vegan", ingredients = listOf("potatoes", "garlic", "onion"))
        val mealsList = listOf(
            firstPotatoMeal,
            secondPotatoMeal,
            Meal(mealName = "cheese cake", ingredients = listOf("flour", "sugar", "milk")),
            Meal(mealName = "mac and cheese", ingredients = listOf("macaroni", "cheese", "milk"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).containsExactly(firstPotatoMeal, secondPotatoMeal)
    }

    @Test
    fun `should return potato meals with case-sensitive potatoes in their ingredients from the meals list`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "greek potatoes  plaki", ingredients = listOf("potatoes", "onion", "canola oil")),
            Meal(mealName = "cream  of cauliflower soup  vegan", ingredients = listOf("POTATOES", "garlic", "onion")),
            Meal(mealName = "easy baked potato soup", ingredients = listOf("PoTatOes", "garlic", "onion"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).hasSize(3)
    }

    @Test
    fun `should return potato meals with potatoes as a substring in their ingredients from the meals list`() {
        // Given
        val mealsList = listOf(
            Meal(mealName = "alouette  potatoes", ingredients = listOf("shallots", "parsley", "new potatoes")),
            Meal(mealName = "cream  of spinach soup", ingredients = listOf("water", "salt", "boiling potatoes"))
        )
        every { mealRepository.getAllMeals() } returns mealsList

        // When
        val potatoMeals = randomPotatoMealsUseCase.get10RandomPotatoMeals()

        // Then
        assertThat(potatoMeals).hasSize(2)
    }
}