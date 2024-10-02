package StructuralDesignPatterns.UseCase2;

public abstract class CondimentDecorator implements Beverage {
    protected Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        if (beverage == null) throw new IllegalArgumentException("Beverage cannot be null");
        this.beverage = beverage;
    }
}
