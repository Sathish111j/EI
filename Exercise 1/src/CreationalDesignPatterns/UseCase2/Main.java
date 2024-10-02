package CreationalDesignPatterns.UseCase2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Shape Factory Started.");

        while (true) {
            System.out.println("Enter shape to draw (circle/square/exit):");
            String shapeType = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(shapeType)) {
                System.out.println("Exiting application.");
                break;
            }

            try {
                Shape shape = factory.getShape(shapeType);
                shape.draw();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
