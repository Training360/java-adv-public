package genericinterface.search;

public interface SearchCriteria<T> {

    boolean pass(T target);
}
