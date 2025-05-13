import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int id;
    private boolean available;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            String status = book.isAvailable() ? "Available" : "Checked Out";
            System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor() + " - " + status);
        }
    }

    public Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void checkoutBook(int id) {
        Book book = findBook(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Book is already checked out.");
        } else {
            book.setAvailable(false);
            System.out.println("You have checked out: " + book.getTitle());
        }
    }

    public void returnBook(int id) {
        Book book = findBook(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("Book was not checked out.");
        } else {
            book.setAvailable(true);
            System.out.println("Thanks for returning: " + book.getTitle());
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 2));
        library.addBook(new Book("1984", "George Orwell", 3));

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Check out a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                library.displayBooks();
            } else if (choice == 2) {
                System.out.print("Enter book ID to check out: ");
                int id = scanner.nextInt();
                library.checkoutBook(id);
            } else if (choice == 3) {
                System.out.print("Enter book ID to return: ");
                int id = scanner.nextInt();
                library.returnBook(id);
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}