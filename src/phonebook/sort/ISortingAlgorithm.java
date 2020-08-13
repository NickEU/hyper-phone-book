package phonebook.sort;

import phonebook.Stopwatch;

import java.util.List;

public interface ISortingAlgorithm {
    boolean trySort(Stopwatch timer, long timeLimit, List<String> directory);
    String getName();
}
