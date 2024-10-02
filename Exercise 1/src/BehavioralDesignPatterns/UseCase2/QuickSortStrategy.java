package BehavioralDesignPatterns.UseCase2;

import java.util.List;

public class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        quickSort(list, 0, list.size() - 1);
        System.out.println("List sorted using Quick Sort.");
    }

    private void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = (low - 1); 
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;

                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}

