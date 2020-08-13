package phonebook.search;

import java.util.List;

public class BinarySearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> searchIn, String searchFor) {
        return true;
    }

    @Override
    public String getName() {
        return "binary";
    }
}
