import java.util.HashMap;
import java.util.Map;

public class ChatRoomManager {
    private static ChatRoomManager instance = null;
    private Map<String, ChatRoom> chatRooms;

    private ChatRoomManager() {
        chatRooms = new HashMap<>();
    }

    public static synchronized ChatRoomManager getInstance() {
        if (instance == null) {
            instance = new ChatRoomManager();
        }
        return instance;
    }

    public ChatRoom getChatRoom(String roomId) {
        if (roomId == null || roomId.isEmpty()) {
            AppLogger.warning("Attempt to get or create chat room with invalid room ID.");
            throw new IllegalArgumentException("Room ID cannot be null or empty.");
        }

        return chatRooms.computeIfAbsent(roomId, id -> new ChatRoom(id));
    }

    public void deleteChatRoom(String roomId) {
        if (roomId == null || roomId.isEmpty()) {
            AppLogger.warning("Attempt to delete chat room with invalid room ID.");
            throw new IllegalArgumentException("Room ID cannot be null or empty.");
        }

        chatRooms.remove(roomId);
        AppLogger.info("Chat room " + roomId + " deleted.");
    }
}
