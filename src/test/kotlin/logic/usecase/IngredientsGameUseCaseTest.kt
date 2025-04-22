package logic.usecase

import io.mockk.every
import io.mockk.mockk
import logic.MealRepository
import logic.model.Meal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import com.google.common.truth.Truth.assertThat


class IngredientsGameUseCaseTest {

    private val mealsRepository: MealRepository = mockk(relaxed = true)
    private lateinit var ingredientsGameUseCase: IngredientsGameUseCase

    @BeforeEach
    fun setup() {
        ingredientsGameUseCase = IngredientsGameUseCase(mealsRepository)
    }

    @Test
    fun `should throw IllegalStateException,When meals with null ingredients found`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(Meal(mealName = "rice"), Meal(mealName = "pizza"))
        //Then
        val exception = assertThrows<IllegalStateException> {
            ingredientsGameUseCase.getRandomIngredients()
        }

        assertEquals(
            expected = "No valid meals with ingredients and name found", actual = exception.message
        )
    }

    @Test
    fun `should throw IllegalStateException,When meals with null name found`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(Meal(ingredients = listOf("")))
        //Then
        val exception = assertThrows<IllegalStateException> {
            ingredientsGameUseCase.getRandomIngredients()
        }

        assertEquals(
            expected = "No valid meals with ingredients and name found", actual = exception.message
        )
    }

    @Test
    fun `Given valid meals,When getting 2wrongIngredients,Then returns 2 ingredients that doesn't exist in the meal`() {
        //Given
        val correctIngredients = listOf("Cheese", "Tomato", "olive oil", "rice", "salt")
        val meals = listOf(
            Meal(mealName = "Pizza", ingredients = listOf("Cheese", "Tomato")),
            Meal(mealName = "Rice", ingredients = listOf("olive oil", "rice", "salt"))
        )
        every { mealsRepository.getAllMeals() } returns meals
        //When
        val result = ingredientsGameUseCase.getRandomIngredients()
        //Then
        assertThat(result.correctIngredient).isIn(correctIngredients)
        assertThat(result.allIngredients).contains(result.correctIngredient)
        assertThat(result.wrongIngredients).doesNotContain(result.correctIngredient)
        assertThat(result.wrongIngredients).hasSize(2)
    }

    @Test
    fun `Given valid meal with all ingredients,When trying to get 2 wrong ingredients,Then throws and IllegalStateException that all meal ingredients matches with the wrong ingredients database`() {
        //Given
        val correctIngredients = listOf(
            "winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt",
            "prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese",
            "ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes",
            "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese",
            "spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon",
            "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"
        )
        val meals = listOf(Meal(mealName = "Pizza", ingredients = correctIngredients))
        every { mealsRepository.getAllMeals() } returns meals

        //Then
        val exception = assertThrows<IllegalStateException> { ingredientsGameUseCase.getRandomIngredients() }
        assertEquals(
            expected = "all meal ingredients matches with the wrong ingredients database",
            actual = exception.message
        )
    }

    // what if get2Wrong ingredients returned list of only 1 element
    @Test
    fun `Given valid meal,When trying to get 2 wrong meals,Then returns a list of only 1 meal`() {
        //Given
        val correctIngredients = listOf(
            "winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt",
            "prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese",
            "ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes",
            "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese",
            "spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon",
            "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"
        )
        val meals = listOf(Meal(mealName = "Pizza", ingredients = correctIngredients.dropLast(1)))
        every { mealsRepository.getAllMeals() } returns meals
        //When
        val result = assertThrows<IllegalStateException> { ingredientsGameUseCase.getRandomIngredients() }
        assertThat(result.message).contains("Not enough wrong ingredients to proceed")

    }

}