package personal.alexgarland.helloworldmvc.testutil

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.Appender
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.slf4j.LoggerFactory
import java.util.*

class LogbackEventChecker {

    private val expectedDetails = ArrayList<LogEventDetails>()

    private val captorLoggingEvent = ArgumentCaptor.forClass(LoggingEvent::class.java)

    fun withExpectedEvent(level: Level, message: String): LogbackEventChecker {
        expectedDetails.add(LogEventDetails(level, message))
        return this
    }

    fun verifyCalls() {

        val numCalls = expectedDetails.size

        verify<Appender<ILoggingEvent>>(mockAppender, times(numCalls)).doAppend(captorLoggingEvent.capture())

        val actualEvents = captorLoggingEvent.allValues

        for (i in 0 until numCalls) {
            val actual = actualEvents[i]
            val expected = expectedDetails[i]

            assertThat(actual.level, `is`(expected.level))
            assertThat(actual.formattedMessage, `is`(expected.message))
        }

    }

    companion object {

        private val ROOT_LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger

        private var mockAppender: Appender<ILoggingEvent>? = null

        fun setup() {

            @Suppress("UNCHECKED_CAST")
            mockAppender = mock(Appender::class.java) as Appender<ILoggingEvent>

            ROOT_LOGGER.addAppender(mockAppender)
        }

        fun teardown() {
            ROOT_LOGGER.detachAppender(mockAppender)
        }
    }

}
