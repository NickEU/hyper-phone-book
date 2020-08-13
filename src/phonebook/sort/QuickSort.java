package phonebook.sort;

import phonebook.Stopwatch;

import java.util.List;

public class QuickSort implements ISortingAlgorithm {
    @Override
    public boolean trySort(Stopwatch timer, List<String> directory) {
        return false;
    }

    @Override
    public String getName() {
        return "quick";
    }
}
