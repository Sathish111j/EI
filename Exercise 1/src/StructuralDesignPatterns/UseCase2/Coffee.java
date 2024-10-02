package StructuralDesignPatterns.UseCase2;

public class Coffee implements Beverage {
    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 2.00;
    }
}
