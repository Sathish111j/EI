package com.chatapp;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private static ChatServer chatServer = ChatServer.getInstance();
    private static User currentUser = null;

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try {
                showMainMenu();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting application.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                if (currentUser != null) {
                    userMenu();
                }
            } catch (Exception e) {
                logger.severe("An error occurred in main menu: " + e.getMessage());
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Welcome to Chat Application ===");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void showUserMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. Create Room");
        System.out.println("2. Join Room");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void userMenu() {
        boolean backToMain = false;
        while (!backToMain) {
            try {
                showUserMenu();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        createRoom();
                        break;
                    case 2:
                        joinRoom();
                        break;
                    case 3:
                        currentUser = null;
                        backToMain = true;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                logger.severe("An error occurred in user menu: " + e.getMessage());
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        User user = chatServer.getUser(username);
        if (user != null) {
            currentUser = user;
            System.out.println("Logged in as " + currentUser.getUsername());
            logger.info("User logged in: " + currentUser.getUsername());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void signUp() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine().trim();
        if (chatServer.getUser(username) == null) {
            User user = new User(username, new HttpAdapter());
            chatServer.addUser(user);
            currentUser = user;
            System.out.println("User created and logged in as " + currentUser.getUsername());
            logger.info("New user signed up: " + currentUser.getUsername());
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void createRoom() {
        System.out.print("Enter room ID: ");
        String roomId = scanner.nextLine().trim();
        ChatRoom room = chatServer.createChatRoom(roomId, currentUser.getUsername());
        if (room != null) {
            room.addUser(currentUser);
            chatRoomMenu(room);
        }
    }

    private static void joinRoom() {
        System.out.println("Available chat rooms:");
        for (ChatRoom room : chatServer.getChatRooms().values()) {
            System.out.println("Room ID: " + room.getRoomId() + ", Created By: " + room.getCreatedBy());
        }
        System.out.print("Enter room ID to join: ");
        String roomId = scanner.nextLine().trim();
        ChatRoom room = chatServer.getChatRoom(roomId);
        if (room != null) {
            room.addUser(currentUser);
            chatRoomMenu(room);
        } else {
            System.out.println("Chat room not found.");
        }
    }

    private static void chatRoomMenu(ChatRoom room) {
        boolean exitRoom = false;
        while (!exitRoom) {
            try {
                showChatRoomMenu();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        System.out.print("Enter message: ");
                        String message = scanner.nextLine().trim();
                        currentUser.sendMessage(message);
                        break;
                    case 2:
                        List<String> activeUsers = room.getActiveUsers();
                        System.out.println("Active users in room:");
                        for (String username : activeUsers) {
                            System.out.println(username);
                        }
                        break;
                    case 3:
                        sendPrivateMessage(room);
                        break;
                    case 4:
                        room.removeUser(currentUser);
                        exitRoom = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                logger.severe("An error occurred in chat room menu: " + e.getMessage());
            }
        }
    }

    private static void showChatRoomMenu() {
        System.out.println("\n=== Chat Room Menu ===");
        System.out.println("1. Send Message");
        System.out.println("2. View Active Users");
        System.out.println("3. Send Private Message");
        System.out.println("4. Leave Room");
        System.out.print("Enter your choice: ");
    }

    private static void sendPrivateMessage(ChatRoom room) {
        System.out.print("Enter username to send private message: ");
        String username = scanner.nextLine().trim();
        User user = chatServer.getUser(username);
        if (user != null && room.getActiveUsers().contains(username)) {
            System.out.print("Enter message: ");
            String content = scanner.nextLine().trim();
            Message message = new Message(currentUser, content);
            user.receiveMessage(message);
            System.out.println("Private message sent to " + username);
            logger.info("Private message sent from " + currentUser.getUsername() + " to " + username);
        } else {
            System.out.println("User not found or not in the room.");
        }
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        return choice;
    }
}
