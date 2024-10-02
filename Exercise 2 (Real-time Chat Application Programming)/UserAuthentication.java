import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("Alice", "password123");
        users.put("Bob", "password456");
        users.put("Charlie", "password789");
    }

    public static boolean authenticate(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            AppLogger.warning("Invalid input for authentication: Empty username or password");
            return false;
        }

        boolean isAuthenticated = users.containsKey(username) && users.get(username).equals(password);

        if (!isAuthenticated) {
            AppLogger.warning("Failed login attempt for user: " + username);
        } else {
            AppLogger.info("User " + username + " successfully logged in.");
        }

        return isAuthenticated;
    }

    public static void register(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            AppLogger.warning("Invalid registration attempt: Empty username or password");
            return;
        }

        users.put(username, password);
        AppLogger.info("User " + username + " registered successfully.");
    }
}
