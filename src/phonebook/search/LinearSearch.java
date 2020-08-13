package phonebook.search;

import java.util.List;

public class LinearSearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> searchIn, String searchFor) {
        for (String name : searchIn) {
            if (searchFor.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
