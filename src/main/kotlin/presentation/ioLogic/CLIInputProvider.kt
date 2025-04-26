package presentation.ioLogic

class CLIInputProvider: UserInputProvider {

    override fun getUserInput(): String? {
        return readlnOrNull()
    }
}