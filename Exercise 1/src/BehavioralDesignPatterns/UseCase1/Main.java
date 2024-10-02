package BehavioralDesignPatterns.UseCase1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();
        Scanner scanner = new Scanner(System.in);

        System.out.println("News Publisher System Started.");

        while (true) {
            System.out.println("Enter command (subscribe/unsubscribe/publish/exit):");
            String command = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(command)) {
                System.out.println("Exiting system.");
                break;
            }

            switch (command) {
                case "subscribe":
                    System.out.println("Enter subscriber name:");
                    String nameToSubscribe = scanner.nextLine().trim();
                    publisher.subscribe(new EmailSubscriber(nameToSubscribe));
                    break;

                case "unsubscribe":
                    System.out.println("Enter subscriber name:");
                    String nameToUnsubscribe = scanner.nextLine().trim();
                    publisher.unsubscribe(new EmailSubscriber(nameToUnsubscribe));
                    break;

                case "publish":
                    System.out.println("Enter article title:");
                    String articleTitle = scanner.nextLine().trim();
                    publisher.publishArticle(articleTitle);
                    break;

                default:
                    System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }
}
