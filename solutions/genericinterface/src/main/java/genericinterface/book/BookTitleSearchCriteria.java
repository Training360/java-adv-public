package genericinterface.book;


import genericinterface.search.SearchCriteria;

public class BookTitleSearchCriteria implements SearchCriteria<Book> {

    private String title;

    public BookTitleSearchCriteria(String title) {
        this.title = title;
    }

    @Override
    public boolean pass(Book target) {
        return target.getTitle().equals(title);
    }
}
