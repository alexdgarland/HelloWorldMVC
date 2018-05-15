package personal.alexgarland.helloworldmvc.testutil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.mockito.ArgumentCaptor;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

public class LogbackEventChecker {

  private static final Logger ROOT_LOGGER =
      (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

  private static Appender<ILoggingEvent> mockAppender;

  @SuppressWarnings("unchecked")
  public static void setup() {
    mockAppender = mock(Appender.class);
    ROOT_LOGGER.addAppender(mockAppender);
  }

  public static void teardown() {
    ROOT_LOGGER.detachAppender(mockAppender);
  }

  private final List<LogEventDetails> expectedDetails = new ArrayList<LogEventDetails>();

  private final ArgumentCaptor<LoggingEvent> captorLoggingEvent =
      ArgumentCaptor.forClass(LoggingEvent.class);

  public LogbackEventChecker withExpectedEvent(Level level, String message) {
    expectedDetails.add(new LogEventDetails(level, message));
    return this;
  }

  public void verifyCalls() {

    int numCalls = expectedDetails.size();

    verify(mockAppender, times(numCalls)).doAppend(captorLoggingEvent.capture());

    List<LoggingEvent> actualEvents = captorLoggingEvent.getAllValues();

    for (int i = 0; i < numCalls; i++) {
      ILoggingEvent actual = actualEvents.get(i);
      LogEventDetails expected = expectedDetails.get(i);

      assertThat(actual.getLevel(), is(expected.getLevel()));
      assertThat(actual.getFormattedMessage(), is(expected.getMessage()));
    }

  }

}
