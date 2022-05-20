package genericinterface.book;

import genericinterface.search.Item;

public class Book implements Item {

    private String author;

    private String title;

    public Book(String author, String title){
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
