package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<String, Book> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link Book} into the system, the book is store in a Map that contains
     * the {@link Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (books.containsKey(book.getIsbn())){
            System.out.print("Ya existe un libro con ese Isbn");
            return false;
        }
        books.put(book.getIsbn(),book);
        return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        // Verificar si el libro existe en la biblioteca
        if (!books.containsKey(isbn)) {
            System.out.println("El libro con ISBN " + isbn + " no existe en la biblioteca.");
            return null;
        }

        // Verificar si el libro está disponible
        if (!isBookAvailable(isbn)) {
            System.out.println("El libro con ISBN " + isbn + " no está disponible.");
            return null;
        }

        // Buscar el usuario por su ID
        User user = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);

        // Verificar si el usuario existe
        if (user == null) {
            System.out.println("El usuario con ID " + userId + " no existe.");
            return null;
        }

        // Verificar si el usuario ya tiene un préstamo activo para el mismo libro
        boolean hasActiveLoan = loans.stream()
                .anyMatch(loan -> loan.getUser().getId().equals(userId) &&
                        loan.getBook().getIsbn().equals(isbn) &&
                        loan.getStatus() == LoanStatus.ACTIVE);

        if (hasActiveLoan) {
            System.out.println("El usuario ya tiene un préstamo activo para el libro con ISBN " + isbn + ".");
            return null;
        }

        // Crear el préstamo
        Loan loan = new Loan();
        loan.setBook(books.get(isbn)); // Asignar el libro
        loan.setUser(user); // Asignar el usuario
        loan.setStatus(LoanStatus.ACTIVE); // Establecer el estado como ACTIVO
        loan.setLoanDate(LocalDateTime.now()); // Establecer la fecha de préstamo

        // Agregar el préstamo a la lista de préstamos
        loans.add(loan);

        // Devolver el préstamo creado
        return loan;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        // Verificar si el préstamo existe en la lista de préstamos
        if (!loans.contains(loan)) {
            System.out.println("El préstamo no existe en la biblioteca.");
            return null;
        }

        // Verificar si el préstamo ya está devuelto
        if (loan.getStatus() == LoanStatus.RETURNED) {
            System.out.println("El préstamo ya ha sido devuelto.");
            return null;
        }

        // Marcar el préstamo como devuelto
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());

        // Devolver el préstamo actualizado
        return loan;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean isBookAvailable(String isbn) {
        // Verificar si el libro existe
        if (!books.containsKey(isbn)) {
            return false;
        }
        // Verificar si hay préstamos activos para este libro
        return !loans.stream()
                .anyMatch(loan -> loan.getBook().getIsbn().equals(isbn) && loan.getStatus() == LoanStatus.ACTIVE);
    }

}
