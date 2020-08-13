package phonebook.search;

import java.util.List;

public class LinearSearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> directory, String searchFor) {
        for (String name : directory) {
            if (searchFor.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
