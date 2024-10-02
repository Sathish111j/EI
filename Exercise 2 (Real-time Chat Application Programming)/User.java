public class User {
    private String username;
    private ChatRoom chatRoom;

    public User(String username) {
        if (username == null || username.isEmpty()) {
            AppLogger.warning("Invalid username provided for User object.");
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    public void joinChatRoom(ChatRoom chatRoom) {
        if (chatRoom == null) {
            AppLogger.warning("User " + username + " attempted to join a null chat room.");
            throw new IllegalArgumentException("Chat room cannot be null.");
        }
        this.chatRoom = chatRoom;
        chatRoom.joinRoom(this);
        AppLogger.info("User " + username + " joined chat room.");
    }

    public void leaveChatRoom() {
        if (chatRoom != null) {
            chatRoom.leaveRoom(this);
            AppLogger.info("User " + username + " left chat room.");
            chatRoom = null;
        }
    }

    public void sendMessage(String message) {
        if (message == null || message.isEmpty()) {
            AppLogger.warning("User " + username + " attempted to send an empty message.");
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }

        if (chatRoom != null) {
            chatRoom.broadcast(username + ": " + message);
            AppLogger.info("User " + username + " sent a message.");
        } else {
            AppLogger.warning("User " + username + " attempted to send a message without being in a chat room.");
        }
    }

    public void sendPrivateMessage(String recipient, String message) {
        if (message == null || message.isEmpty()) {
            AppLogger.warning("User " + username + " attempted to send an empty private message.");
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }

        if (chatRoom != null) {
            chatRoom.privateMessage(this, recipient, message);
        } else {
            AppLogger.warning("User " + username + " attempted to send a private message without being in a chat room.");
        }
    }

    public void update(String message) {
        System.out.println("[" + username + "]: " + message);
    }

    public String getUsername() {
        return username;
    }
}
