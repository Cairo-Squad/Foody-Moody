package presentation.cliController

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import presentation.Command
import presentation.ioLogic.OutputHandler
import kotlin.test.Test

class CLIDispatcherTest {
    private lateinit var outputHandler: OutputHandler
    private lateinit var command1: Command
    private lateinit var command2: Command
    private lateinit var cliDispatcher: CLIDispatcher

    @BeforeEach
    fun setUp() {
        outputHandler = mockk(relaxed = true)
        command1 = mockk(relaxed = true)
        command2 = mockk(relaxed = true)

        every { command1.id } returns 1
        every { command2.id } returns 2

        val commandsMap = mapOf(
            command1.id to command1,
            command2.id to command2
        )

        cliDispatcher = CLIDispatcher(commandsMap, outputHandler)
    }

    @Test
    fun `dispatch should execute correct command when a valid user input is provided`() {
        cliDispatcher.dispatch(1)

        verify(exactly = 1) { command1.execute() }
        verify(exactly = 0) { command2.execute() }
        verify(exactly = 0) { outputHandler.printlnMessage(any()) }
    }

    @Test
    fun `dispatch should print error message when an invalid user input is provided`() {
        cliDispatcher.dispatch(99)

        verify { outputHandler.printlnMessage(CLIConstants.COMMON_ERROR_MESSAGE) }
        verify(exactly = 0) { command1.execute() }
        verify(exactly = 0) { command2.execute() }
    }

    @Test
    fun `validateOption should return true for a valid command id`() {
        assertThat(cliDispatcher.validateOption(1)).isTrue()
    }

    @Test
    fun `validateOption should return true for exit command id`() {
        assertThat(cliDispatcher.validateOption(UserOptions.EXIT_COMMAND_CODE)).isTrue()
    }

    @Test
    fun `validateOption should return false for an invalid command id`() {
        assertThat(cliDispatcher.validateOption(99)).isFalse()
    }
}