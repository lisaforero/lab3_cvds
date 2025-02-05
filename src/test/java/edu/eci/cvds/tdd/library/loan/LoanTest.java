package edu.eci.cvds.tdd.library.loan;

import static edu.eci.cvds.tdd.library.loan.LoanStatus.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LoanTest {

    @Test
    public void testCreateLoan(){
        Book book1 = new Book("Titulo1","Autor1","ISBM03");
        User user1 = new User("Juan Perez", "1234");
        LocalDateTime loanDate = LocalDateTime.parse("2025-02-01 12:30");
        LoanStatus status = ACTIVE;
        LocalDateTime returnDate = LocalDateTime.parse("2025-02-05 11:00");

        Loan loan1 = new Loan();
        loan1.setBook(book1);
        loan1.setUser(user1);
        loan1.setLoanDate(loanDate);
        loan1.setStatus(status);
        loan1.setReturnDate(returnDate);

        assertEquals(book1, loan1.getBook());
        assertEquals(user1, loan1.getUser());
        assertEquals(loanDate, loan1.getLoanDate());
        assertEquals(status, loan1.getStatus());
        assertEquals(returnDate, loan1.getReturnDate());
    }

}