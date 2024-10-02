import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ChatRoomManager manager = ChatRoomManager.getInstance();

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (!UserAuthentication.authenticate(username, password)) {
                System.out.println("Invalid credentials. Please try again.");
                return;
            }

            User user = new User(username);

            System.out.print("Enter chat room ID to join: ");
            String roomId = scanner.nextLine();
            ChatRoom chatRoom = manager.getChatRoom(roomId);
            user.joinChatRoom(chatRoom);

            String message;
            System.out.println("Start chatting (type 'exit' to leave, 'private' for private message):");
            while (!(message = scanner.nextLine()).equalsIgnoreCase("exit")) {
                if (message.startsWith("private")) {
                    System.out.print("Enter recipient's username: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter your message: ");
                    String privateMessage = scanner.nextLine();
                    user.sendPrivateMessage(recipient, privateMessage);
                } else {
                    user.sendMessage(message);
                }
            }

            user.leaveChatRoom();
            System.out.println("You have left the chat.");
        } catch (Exception e) {
            AppLogger.error("An error occurred in the chat application", e);
        }
    }
}
