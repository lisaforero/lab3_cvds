package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {
    private static Library library;
    @BeforeAll
    public static void init(){
        library = new Library();
    }


    @Test
    public void testTrueAddBook(){
        Book book2 = new Book("Titulo2","Autor2","ISBM02");
        assertTrue(library.addBook(book2));
    }

    @Test
    public void testFalseAddBook(){
        Book book1 = new Book("Titulo1","Autor1","ISBM02");
        Book book2 = new Book("Titulo2","Autor2","ISBM02");
        library.addBook(book1);
        assertFalse(library.addBook(book2));
    }
}
