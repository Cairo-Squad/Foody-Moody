package presentation.cliController

object UserInputHandler {

    // TODO: If you need to take any user input for your feature, implement its function here and use it inside the dispatcher
    fun getUserInput(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }

    fun getStringUserInput(): String? {
        return readlnOrNull()
    }

}