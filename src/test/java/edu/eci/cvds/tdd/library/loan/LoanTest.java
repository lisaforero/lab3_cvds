package edu.eci.cvds.tdd.library.loan;

import static edu.eci.cvds.tdd.library.loan.LoanStatus.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LoanTest {

    private Loan loan;
    private Book book;
    private User user;
    private LoanStatus status;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        book = new Book("Clean Code", "Robert Martin", "123456789");
        user = new User();
        user.setName("John Perez");
        user.setId("12345");
        status = LoanStatus.ACTIVE;
        now = LocalDateTime.now();

        loan = new Loan(book, user, now, status);
    }

    @Test
    void testSetAndGetBook() {
        Book newBook = new Book("Java Book", "Autor Desconocido", "987654321");

        loan.setBook(newBook);
        
        assertEquals(newBook, loan.getBook());
    }

    @Test
    void testSetAndGetUser() {
        User newUser = new User();
        newUser.setName("Jane Sanchez");
        newUser.setId("54321");

        loan.setUser(newUser);

        assertEquals(newUser, loan.getUser());
    }

    @Test
    void testSetAndGetLoanDate() {
        LocalDateTime newLoanDate = now.plusDays(3);

        loan.setLoanDate(newLoanDate);

        assertEquals(newLoanDate, loan.getLoanDate());
    }

    @Test
    void testSetAndGetStatus() {
        LoanStatus newStatus = LoanStatus.RETURNED;

        loan.setStatus(newStatus);

        assertEquals(newStatus, loan.getStatus());
    }

    @Test
    void testSetAndGetReturnDate() {
        LocalDateTime returnDate = now.plusDays(5);

        loan.setReturnDate(returnDate);

        assertEquals(returnDate, loan.getReturnDate());
    }

    @Test
    void testBookEquals() {
        Book sameBook = new Book("Clean Code", "Robert Martin", "123456789");

        assertEquals(book, sameBook);

        Book differentBook = new Book("Nuevo Libro", "Nuevo Autor", "97654321");

        assertNotEquals(book, differentBook);
    }
}