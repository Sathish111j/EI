package com.chatapp;

import java.util.*;
import java.util.logging.Logger;

public class User implements Observer {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private String username;
    private ProtocolAdapter protocolAdapter;
    private List<Message> messageHistory;
    private ChatRoom currentChatRoom;

    public User(String username, ProtocolAdapter protocolAdapter) {
        this.username = username;
        this.protocolAdapter = protocolAdapter;
        this.messageHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setCurrentChatRoom(ChatRoom chatRoom) {
        this.currentChatRoom = chatRoom;
    }

    public void sendMessage(String content) {
        if (currentChatRoom != null) {
            Message message = new Message(this, content);
            currentChatRoom.broadcastMessage(message);
            logger.info("User " + username + " sent message in room " + currentChatRoom.getRoomId());
        } else {
            System.out.println("You are not in any chat room.");
        }
    }

    public void receiveMessage(Message message) {
        messageHistory.add(message);
        System.out.println("[" + message.getSender().getUsername() + "]: " + message.getContent());
    }

    @Override
    public void update(Message message) {
        receiveMessage(message);
    }
}
