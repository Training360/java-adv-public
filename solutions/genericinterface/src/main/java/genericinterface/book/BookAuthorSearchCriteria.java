package genericinterface.book;

import genericinterface.search.SearchCriteria;

public class BookAuthorSearchCriteria implements SearchCriteria<Book> {

    private String author;

    public BookAuthorSearchCriteria(String author) {
        this.author = author;
    }

    @Override
    public boolean pass(Book target) {
        return target.getAuthor().equals(author);
    }
}
