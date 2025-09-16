import java.util.*;
import java.util.stream.Collectors;


// ===== Book =====
class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        if (title == null || author == null || isbn == null) {
            throw new IllegalArgumentException("Title, author, and ISBN must be non-null.");
        }
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public void checkOut() {
        if (!available) throw new IllegalStateException("Book already checked out.");
        available = false;
    }

    public void returnBook() {
        if (available) throw new IllegalStateException("Book is not checked out.");
        available = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN:%s) [%s]",
                title, author, isbn, available ? "Available" : "Out");
    }
}

// ===== Patron =====
class Patron {
    private final String name;
    private final String id;
    private final List<Book> checkedOutBooks = new ArrayList<>();

    public Patron(String name, String id) {
        if (name == null || id == null) throw new IllegalArgumentException("Name and ID must be non-null.");
        this.name = name;
        this.id = id;
    }

    public void checkOutBook(Book book) { checkedOutBooks.add(book); }
    public void returnBook(Book book) { checkedOutBooks.remove(book); }

    public String getName() { return name; }
    public String getId() { return id; }
    public List<Book> getCheckedOutBooks() { return List.copyOf(checkedOutBooks); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patron)) return false;
        Patron patron = (Patron) o;
        return id.equals(patron.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return name + " (ID:" + id + ")"; }
}

// ===== Library =====
class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        if (b == null) throw new IllegalArgumentException("Book cannot be null.");
        if (books.contains(b)) throw new IllegalArgumentException("Book with same ISBN already exists.");
        books.add(b);
    }

    public void removeBook(String isbn) {
        Book toRemove = searchBookByISBN(isbn);
        if (toRemove == null) throw new NoSuchElementException("No book found with ISBN " + isbn);
        if (!toRemove.isAvailable()) throw new IllegalStateException("Cannot remove a checked-out book.");
        books.remove(toRemove);
    }

    public Book searchBookByISBN(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b; // found the first match
            }
        }
        return null; // not found
    }

    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book b : books) {
            System.out.println("- " + b);
        }
    }
}

// ===== Checkout =====
class Checkout {
    private final Map<Book, Patron> checkouts = new HashMap<>();

    public void checkOut(Book book, Patron patron) {
        if (book == null || patron == null) throw new IllegalArgumentException("Book and Patron are required.");
        if (!book.isAvailable()) throw new IllegalStateException("Book is not available.");
        if (checkouts.containsKey(book)) throw new IllegalStateException("Book already checked out.");

        book.checkOut();
        checkouts.put(book, patron);
        patron.checkOutBook(book);
    }

    public void returnBook(Book book) {
        Patron holder = checkouts.remove(book);
        if (holder == null) throw new NoSuchElementException("This book is not currently checked out.");
        book.returnBook();
        holder.returnBook(book);
    }

    public void displayCheckouts() {
        if (checkouts.isEmpty()) {
            System.out.println("No active checkouts.");
            return;
        }
        checkouts.forEach((book, patron) ->
                System.out.println(patron + " -> " + book));
    }

    public Patron whoHas(Book book) { return checkouts.get(book); }
}


public class Main {
    public static void main(String[] args) {
        // Seed data
        Library lib = new Library();
        Book b1 = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        Book b2 = new Book("Effective Java", "Joshua Bloch", "9780134685991");
        Book b3 = new Book("Design Patterns", "GoF", "9780201633610");
        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        Patron p1 = new Patron("Rounak Mukherjee", "U001");
        Patron p2 = new Patron("Sunanda S.", "U002");

        Checkout checkout = new Checkout();

        System.out.println("== All Books ==");
        lib.displayBooks();

        System.out.println("\n== Search by Author: 'Joshua' ==");
        lib.searchByAuthor("Joshua").forEach(System.out::println);

        System.out.println("\n== Checkout ==");
        checkout.checkOut(b2, p1); // Rounak takes Effective Java
        checkout.displayCheckouts();

        System.out.println("\n== Try removing a checked-out book (should fail) ==");
        try {
            lib.removeBook("9780134685991");
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }

        System.out.println("\n== Return Book ==");
        checkout.returnBook(b2);
        checkout.displayCheckouts();

        System.out.println("\n== Remove a book now that it's available ==");
        lib.removeBook("9780134685991");
        lib.displayBooks();
    }
}
