package StructuralDesignPatterns.UseCase2;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Coffee();
        System.out.println(beverage.getDescription() + " $" + beverage.getCost());

        beverage = new Milk(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.getCost());

        beverage = new Sugar(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.getCost());
    }
}
