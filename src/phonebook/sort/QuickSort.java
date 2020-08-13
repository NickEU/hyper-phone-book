package phonebook.sort;

import phonebook.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort implements ISortingAlgorithm {
    @Override
    public boolean trySort(Stopwatch timer, List<String> directory) {
        List<String> result = new ArrayList<>(directory);
        try {
            result = quickSort(timer, result);
        } catch (IllegalStateException i) {
            return false;
        }
        Collections.copy(directory, result);
        return true;
    }

    private List<String> quickSort(Stopwatch timer, List<String> sortMe) {
        List<String> smaller = new ArrayList<>();
        List<String> bigger = new ArrayList<>();
        String pivot = sortMe.get((sortMe.size() - 1) / 2);
        int pivotsRemoved = 0;
        if (timer.wentOverLimit()) {
            throw new IllegalStateException();
        }
        for (String listing : sortMe) {
            if (listing.equals(pivot)) {
                pivotsRemoved++;
            } else if (listing.compareTo(pivot) < 0) {
                smaller.add(listing);
            } else {
                bigger.add(listing);
            }

        }
        if (smaller.size() > 1) {
            smaller = quickSort(timer, smaller);
        }
        if (bigger.size() > 1) {
            bigger = quickSort(timer, bigger);
        }
        smaller.addAll(Collections.nCopies(pivotsRemoved, pivot));
        return Stream.concat(smaller.stream(), bigger.stream()).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return "quick";
    }
}
