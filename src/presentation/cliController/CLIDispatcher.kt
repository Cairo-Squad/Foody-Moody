package presentation.cliController

import logic.GetMealsForLargeGroupUseCase

class CLIDispatcher(
    private val getMealsForLargeGroupUseCase : GetMealsForLargeGroupUseCase
) {

    // TODO: Map your feature's command code to its function here
    private val commands = mapOf<Int, () -> Unit>(
        15 to ::getMealsForLargeGroup
    )

    fun getMealsForLargeGroup() {
        getMealsForLargeGroupUseCase.getAllMealsForLargeGroup().forEachIndexed { index, meal ->
            println("meal ${index+1} is: $meal")
        }
    }

    fun dispatch(userInput: Int) {
        val command = commands[userInput]
        if (command != null) {
            command()
        } else {
            println(CLIConstants.COMMON_ERROR_MESSAGE)
        }
    }

    fun validateOption(option: Int): Boolean {
        return option == CLIConstants.EXIT_COMMAND_CODE || option in commands.keys
    }

    // TODO: Implement your feature here as a private function and map it in the above map
}