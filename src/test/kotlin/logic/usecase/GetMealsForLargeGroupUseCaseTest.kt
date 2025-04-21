package logic.usecase

import io.mockk.mockk
import logic.MealRepository
import org.junit.jupiter.api.BeforeEach

class GetMealsForLargeGroupUseCaseTest {

    private lateinit var repository: MealRepository
    private lateinit var getMealsForLargeGroupUseCase: GetMealsForLargeGroupUseCase
    @BeforeEach
    fun setUp() {
        repository = mockk(relaxed = true)
        getMealsForLargeGroupUseCase = GetMealsForLargeGroupUseCase(repository)
    }

}