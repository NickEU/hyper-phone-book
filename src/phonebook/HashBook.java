package phonebook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class HashBook {
    private final Set<String> people;

    HashBook(List<String> unsortedDirectory) {
        this.people = new HashSet<>(unsortedDirectory);
    }

    boolean hasEntry(String entry) {
        return people.contains(entry);
    }
}
