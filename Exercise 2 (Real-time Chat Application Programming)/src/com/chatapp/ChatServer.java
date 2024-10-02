package com.chatapp;

import java.util.*;
import java.util.logging.Logger;

public class ChatServer {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private static ChatServer instance = null;
    private Map<String, ChatRoom> chatRooms;
    private Map<String, User> users;

    private ChatServer() {
        chatRooms = new HashMap<>();
        users = new HashMap<>();
        initializeDefaultData();
    }

    public static synchronized ChatServer getInstance() {
        if (instance == null) {
            instance = new ChatServer();
        }
        return instance;
    }

    private void initializeDefaultData() {
        // Initialize default users
        User user1 = new User("Alice", new HttpAdapter());
        User user2 = new User("Bob", new HttpAdapter());
        User user3 = new User("Charlie", new HttpAdapter());
        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
        users.put(user3.getUsername(), user3);

        // Initialize default chat rooms
        ChatRoom room1 = new ChatRoom("Room1", "Alice");
        ChatRoom room2 = new ChatRoom("Room2", "Bob");
        chatRooms.put(room1.getRoomId(), room1);
        chatRooms.put(room2.getRoomId(), room2);

        logger.info("Default data initialized.");
    }

    public Map<String, ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            logger.info("User added: " + user.getUsername());
        } else {
            System.out.println("User already exists.");
        }
    }

    public ChatRoom createChatRoom(String roomId, String createdBy) {
        if (!chatRooms.containsKey(roomId)) {
            ChatRoom room = new ChatRoom(roomId, createdBy);
            chatRooms.put(roomId, room);
            logger.info("Chat room created: " + roomId + " by " + createdBy);
            return room;
        } else {
            System.out.println("Chat room already exists.");
            return null;
        }
    }

    public ChatRoom getChatRoom(String roomId) {
        return chatRooms.get(roomId);
    }
}
