package personal.alexgarland.helloworldmvc.testutil;

import ch.qos.logback.classic.Level;

class LogEventDetails {

  private final Level level;
  private final String message;

  public LogEventDetails(Level level, String message) {
    this.level = level;
    this.message = message;
  }

  public Level getLevel() {
    return level;
  }

  public String getMessage() {
    return message;
  }

}
