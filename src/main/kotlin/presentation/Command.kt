package presentation

interface Command {
    val id: Int
    fun execute()
}