package phonebook.sort;

import phonebook.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickSort implements ISortingAlgorithm {
    @Override
    public boolean trySort(Stopwatch timer, List<String> directory) {
        List<String> result = new ArrayList<>(directory);
        result.sort(Comparator.naturalOrder());
        Collections.copy(directory, result);
        return true;
    }

    @Override
    public String getName() {
        return "quick";
    }
}
