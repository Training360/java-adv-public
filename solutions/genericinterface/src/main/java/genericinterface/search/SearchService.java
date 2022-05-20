package genericinterface.search;

import java.util.List;

public class SearchService<T extends Item> {

    public T findFirst(List<T> items, SearchCriteria<T> criteria) {
        for (T item: items) {
            if (criteria.pass(item)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item not found based on search criteria.");
    }
}
