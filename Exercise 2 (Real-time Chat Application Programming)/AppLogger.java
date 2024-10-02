import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {
    private static Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void warning(String message) {
        log(Level.WARNING, message);
    }

    public static void error(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }
}
