package com.chatapp;

import java.util.*;
import java.util.logging.Logger;

public class ChatRoom implements Subject {
    private static final Logger logger = Logger.getLogger(ChatRoom.class.getName());
    private String roomId;
    private String createdBy;
    private Map<String, User> activeUsers;
    private List<Message> messageHistory;
    private List<Observer> observers;

    public ChatRoom(String roomId, String createdBy) {
        this.roomId = roomId;
        this.createdBy = createdBy;
        this.activeUsers = new HashMap<>();
        this.messageHistory = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void addUser(User user) {
        if (user != null && !activeUsers.containsKey(user.getUsername())) {
            activeUsers.put(user.getUsername(), user);
            registerObserver(user);
            user.setCurrentChatRoom(this);
            System.out.println(user.getUsername() + " has joined the room.");
            logger.info(user.getUsername() + " joined room " + roomId);
        }
    }

    public void removeUser(User user) {
        if (user != null && activeUsers.containsKey(user.getUsername())) {
            activeUsers.remove(user.getUsername());
            removeObserver(user);
            user.setCurrentChatRoom(null);
            System.out.println(user.getUsername() + " has left the room.");
            logger.info(user.getUsername() + " left room " + roomId);
        }
    }

    public void broadcastMessage(Message message) {
        if (message != null) {
            messageHistory.add(message);
            notifyObservers(message);
            logger.info("Message broadcasted in room " + roomId + " by " + message.getSender().getUsername());
        }
    }

    public List<String> getActiveUsers() {
        return new ArrayList<>(activeUsers.keySet());
    }

    public List<Message> getMessageHistory() {
        return messageHistory;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            logger.info("Observer registered: " + ((User) observer).getUsername());
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
            logger.info("Observer removed: " + ((User) observer).getUsername());
        }
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
