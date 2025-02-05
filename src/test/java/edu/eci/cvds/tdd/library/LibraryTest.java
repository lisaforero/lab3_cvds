package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {
    private static Library library;
    private static Book book1,book2,book3;

    @BeforeAll
    public static void init(){
        library = new Library();
        book1 = new Book("Titulo1","Autor1","ISBM01");
        book2 = new Book("Titulo2","Autor2","ISBM02");
        book3 = new Book("Titulo3","Autor3","ISBM02");
    }


    @Test
    public void testTrueAddBook(){
        assertTrue(library.addBook(book2));
    }

    @Test
    public void testFalseAddBook(){
        library.addBook(book3);
        assertFalse(library.addBook(book2));
    }

    @Test
    public void testLoanABook(){
        String userid = "userid";
        String isbm = "ISBM02";
        Loan loan = new Loan();




    }
}
