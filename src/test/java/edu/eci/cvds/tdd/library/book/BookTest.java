package edu.eci.cvds.tdd.library.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testCreateBook(){
        String title = "Libro rojo";
        String author = "Gaspar Edul";
        String isbn = "84862384";

        Book book = new Book(title,author,isbn);

        assertEquals(title,book.getTittle());
        assertEquals(author,book.getAuthor());
        assertEquals(isbn,book.getIsbn());
    }

    @Test
    public void testEqualIsbm(){

        Book book1 = new Book("Titulo1","Autor1","ISBM02");
        Book book2 = new Book("Titulo2","Autor2","ISBM02");

        assertTrue(book1.equals(book2));
    }

    @Test
    public void testDifIsbm(){

        Book book1 = new Book("Titulo1","Autor1","ISBM01");
        Book book2 = new Book("Titulo2","Autor2","ISBM02");

        assertFalse(book1.equals(book2));
    }

}
