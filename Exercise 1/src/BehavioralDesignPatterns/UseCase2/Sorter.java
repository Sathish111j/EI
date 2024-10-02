package BehavioralDesignPatterns.UseCase2;

import java.util.List;

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        if (strategy == null) throw new IllegalArgumentException("Strategy cannot be null");
        this.strategy = strategy;
    }

    public void sort(List<Integer> list) {
        if (strategy == null) throw new IllegalStateException("Sorting strategy not set");
        strategy.sort(list);
    }
}
