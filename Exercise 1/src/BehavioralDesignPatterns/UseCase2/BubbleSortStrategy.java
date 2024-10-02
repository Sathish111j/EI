package BehavioralDesignPatterns.UseCase2;

import java.util.List;

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Swap
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        System.out.println("List sorted using Bubble Sort.");
    }
}
