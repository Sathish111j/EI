import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom {
    private String roomId;
    private List<User> users;
    private List<String> messageHistory;

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.users = Collections.synchronizedList(new ArrayList<>()); 
        this.messageHistory = new ArrayList<>();
        AppLogger.info("Chat room " + roomId + " created.");
    }

    public synchronized void joinRoom(User user) {
        if (user == null) {
            AppLogger.warning("Invalid user trying to join room.");
            throw new IllegalArgumentException("User cannot be null.");
        }

        users.add(user);
        AppLogger.info("User " + user.getUsername() + " joined chat room " + roomId);
        user.update("Welcome to room " + roomId + "!");

        // Send message history
        for (String message : messageHistory) {
            user.update(message);
        }
        broadcast(user.getUsername() + " joined the room.");
    }

    public synchronized void leaveRoom(User user) {
        if (user == null) {
            AppLogger.warning("Invalid user trying to leave room.");
            throw new IllegalArgumentException("User cannot be null.");
        }

        users.remove(user);
        AppLogger.info("User " + user.getUsername() + " left chat room " + roomId);
        broadcast(user.getUsername() + " left the room.");
    }

    public synchronized void broadcast(String message) {
        if (message == null || message.isEmpty()) {
            AppLogger.warning("Attempt to broadcast an empty message.");
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }

        messageHistory.add(message);
        AppLogger.info("Broadcasting message: " + message);
        for (User user : users) {
            try {
                user.update(message);
            } catch (Exception e) {
                AppLogger.error("Failed to send message to user " + user.getUsername(), e);
            }
        }
    }

    public synchronized void privateMessage(User sender, String recipientUsername, String message) {
        if (message == null || message.isEmpty() || recipientUsername == null || recipientUsername.isEmpty()) {
            AppLogger.warning("Invalid private message input.");
            throw new IllegalArgumentException("Message or recipient cannot be null or empty.");
        }

        for (User user : users) {
            if (user.getUsername().equals(recipientUsername)) {
                AppLogger.info("Private message from " + sender.getUsername() + " to " + recipientUsername);
                user.update("Private from " + sender.getUsername() + ": " + message);
                return;
            }
        }
        AppLogger.warning("Private message recipient " + recipientUsername + " not found.");
        sender.update("User " + recipientUsername + " not found.");
    }
}
