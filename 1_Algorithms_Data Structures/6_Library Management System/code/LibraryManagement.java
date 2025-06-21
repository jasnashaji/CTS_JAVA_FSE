import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "[" + bookId + "] " + title + " by " + author;
    }
}

public class LibraryManagement {
    static Book[] books = {
        new Book(1, "The Alchemist", "Paulo Coelho"),
        new Book(2, "1984", "George Orwell"),
        new Book(3, "Pride and Prejudice", "Jane Austen"),
        new Book(4, "To Kill a Mockingbird", "Harper Lee"),
        new Book(5, "Moby-Dick", "Herman Melville")
    };

    // Linear Search
    public static Book linearSearch(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search (Assumes sorted array)
    public static Book binarySearch(String title) {
        Arrays.sort(books, Comparator.comparing(b -> b.title.toLowerCase()));  // Sort alphabetically
        int left = 0, right = books.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);

            if (cmp == 0) return books[mid];
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("=== Linear Search ===");
        Book result1 = linearSearch("1984");
        System.out.println(result1 != null ? result1 : "Book not found");

        System.out.println("\n=== Binary Search ===");
        Book result2 = binarySearch("Moby-Dick");
        System.out.println(result2 != null ? result2 : "Book not found");
    }
}
