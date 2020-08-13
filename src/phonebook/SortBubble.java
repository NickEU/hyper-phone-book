package phonebook;

import java.util.List;

class SortBubble {
    static boolean trySort(Stopwatch timer, long bubbleSortTimeLimit, List<String> directory) {
        for (int i = 0; i < directory.size(); i++) {
            for (int j = 0; j < directory.size() - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    swap(j, j + 1, directory);
                }
            }
            if (timer.getElapsed() > bubbleSortTimeLimit) {
                return false;
            }
        }
        return true;
    }

    static void swap(int j, int i, List<String> directory) {
        String temp = directory.get(j);
        directory.set(j, directory.get(i));
        directory.set(i, temp);
    }
}
