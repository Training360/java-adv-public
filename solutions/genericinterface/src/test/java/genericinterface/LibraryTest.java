package genericinterface;

import genericinterface.search.SearchService;
import genericinterface.book.Book;
import genericinterface.book.BookAuthorSearchCriteria;
import genericinterface.book.BookTitleSearchCriteria;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LibraryTest {


    public static final List<Book> BOOKS = Arrays.asList(
            new Book("Gárdonyi Géza", "Egri csillagok"),
            new Book("Szophoklész", "Antigoné"));

    @Test
    public void shouldThrowExceptionIfBookNotPresent() {
        assertThrows(IllegalArgumentException.class, () -> new SearchService<Book>().findFirst(BOOKS, new BookTitleSearchCriteria("Cinderella")));
    }

    @Test
    public void shouldFindAndReturnBookByTitle() {
        Book book = new SearchService<Book>().findFirst(BOOKS, new BookTitleSearchCriteria("Egri csillagok"));

        assertEquals("Gárdonyi Géza", book.getAuthor());
    }

    @Test
    public void shouldFindAndReturnBookByAuthor() {
        Book book = new SearchService<Book>().findFirst(BOOKS, new BookAuthorSearchCriteria("Szophoklész"));

        assertEquals("Antigoné", book.getTitle());
    }


}
