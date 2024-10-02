package CreationalDesignPatterns.UseCase1;

public class Logger {
    private static Logger instance;

    private Logger() {
       
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                    System.out.println("Logger instance created.");
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

