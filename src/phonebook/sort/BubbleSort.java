package phonebook.sort;

import phonebook.Stopwatch;

import java.util.List;

public class BubbleSort implements ISortingAlgorithm {
    private static void swap(int j, int i, List<String> directory) {
        String temp = directory.get(j);
        directory.set(j, directory.get(i));
        directory.set(i, temp);
    }

    @Override
    public boolean trySort(Stopwatch timer, List<String> directory) {
        for (int i = 0; i < directory.size(); i++) {
            for (int j = 0; j < directory.size() - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    swap(j, j + 1, directory);
                }
            }
            if (timer.wentOverLimit()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "bubble";
    }
}
