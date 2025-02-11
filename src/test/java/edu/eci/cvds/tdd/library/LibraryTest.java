package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1, book2, book3;
    private User user1, user2;

    @BeforeEach
    public void init() {
        library = new Library();
        book1 = new Book("Titulo1", "Autor1", "ISBM01");
        book2 = new Book("Titulo2", "Autor2", "ISBM02");
        book3 = new Book("Titulo3", "Autor3", "ISBM02");

        user1 = new User();
        user1.setId("123");
        user1.setName("Usuario1");
        user2 = new User();
        user2.setId("456");
        user2.setName("Usuario2");

        library.addUser(user1);
        library.addUser(user2);
    }

    @Test
    public void testTrueAddBook() {
        assertTrue(library.addBook(book1));
    }

    @Test
    public void testFalseAddBook() {
        library.addBook(book2);
        assertFalse(library.addBook(book3));
    }

    @Test
    public void testLoanABook() {
        library.addBook(book1);

        Loan loan = library.loanABook(user1.getId(), book1.getIsbn());

        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertNotNull(loan.getLoanDate());

        assertFalse(library.isBookAvailable(book1.getIsbn()));
    }

    @Test
    public void testLoanABookWithInvalidUser() {
        library.addBook(book1);
        Loan loan = library.loanABook("999", book1.getIsbn());

        assertNull(loan);
    }

    @Test
    public void testLoanABookWithUnavailableBook() {
        library.addBook(book1);

        library.loanABook(user1.getId(), book1.getIsbn());

        Loan loan = library.loanABook(user2.getId(), book1.getIsbn());

        assertNull(loan);
    }

    @Test
    public void testReturnLoan() {
        library.addBook(book1);

        Loan loan = library.loanABook(user1.getId(), book1.getIsbn());

        Loan returnedLoan = library.returnLoan(loan);

        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
        assertNotNull(returnedLoan.getReturnDate());

        assertTrue(library.isBookAvailable(book1.getIsbn()));
    }

    @Test
    public void testReturnLoanWithInvalidLoan() {
        Loan loan = new Loan();
        loan.setBook(book1);
        loan.setUser(user1);
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanDate(LocalDateTime.now());
        library.addBook(book1);
        library.loanABook(user1.getId(),book1.getIsbn());
        Loan returnedLoan = library.returnLoan(loan);

        assertNull(returnedLoan);
    }
}
