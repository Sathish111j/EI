package com.chatapp;

public interface ProtocolAdapter {
    void sendMessage(Message message);
    void receiveMessage(Message message);
}
