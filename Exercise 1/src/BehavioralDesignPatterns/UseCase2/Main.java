package BehavioralDesignPatterns.UseCase2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sorting Application Started.");

        while (true) {
            System.out.println("Choose sorting strategy (bubble/quick/exit):");
            String choice = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(choice)) {
                System.out.println("Exiting application.");
                break;
            }

            switch (choice) {
                case "bubble":
                    sorter.setStrategy(new BubbleSortStrategy());
                    break;

                case "quick":
                    sorter.setStrategy(new QuickSortStrategy());
                    break;

                default:
                    System.out.println("Invalid strategy choice.");
                    continue;
            }

            System.out.println("Enter numbers to sort (comma-separated):");
            String input = scanner.nextLine();
            String[] tokens = input.split(",");
            List<Integer> numbers = new ArrayList<>();
            try {
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token.trim()));
                }
                sorter.sort(numbers);
                System.out.println("Sorted List: " + numbers);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format.");
            }
        }

        scanner.close();
    }
}
