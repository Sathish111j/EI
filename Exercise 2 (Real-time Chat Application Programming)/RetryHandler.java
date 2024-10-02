import java.util.function.Supplier;

public class RetryHandler {
    private static final int MAX_RETRIES = 3;

    public static <T> T retry(Supplier<T> supplier) throws Exception {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                return supplier.get();
            } catch (Exception e) {
                attempts++;
                AppLogger.warning("Attempt " + attempts + " failed. Retrying...");
                if (attempts == MAX_RETRIES) {
                    AppLogger.error("Max retry limit reached.", e);
                    throw e;
                }
            }
        }
        return null;
    }
}
