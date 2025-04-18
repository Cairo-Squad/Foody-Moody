package presentation.cliController

object UserInputHandler {

    fun getUserInput(): Int? {
        return readlnOrNull()?.toIntOrNull()
    }

    fun getStringUserInput(): String? {
        return readlnOrNull()
    }


}