public class Main {
    static class Book {
        int bookId;
        String title;
        String author;
        double price;

        Book(int bookId, String title, String author, double price) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.price = price;
        }
    }
    public static int removeDuplicates(Book[] books, int n) {
        if (n == 0)
            return 0;

        int j = 0;

        for (int i = 1; i < n; i++) {
            if (books[i].bookId != books[j].bookId) {
                j++;
                books[j] = books[i];
            }
        }

        return j + 1;
    }
    public static void searchByTitle(Book[] books, int count, String query) {
        System.out.println("\nSearch Results for '" + query + "':");
        boolean found = false;
        query = query.toLowerCase();
        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(query)) {
                System.out.println("Found: [" + books[i].bookId + "] "
                        + books[i].title + " (Rs. " + books[i].price + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching book found.");
        }
    }
    public static void sortByPrice(Book[] books, int count) {
        int swaps = 0;

        for (int i = 0; i < count - 1; i++) {

            int min = i;

            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[min].price) {
                    min = j;
                }
            }
            if (min != i) {
                Book temp = books[i];
                books[i] = books[min];
                books[min] = temp;
                swaps++;
            }
        }

        System.out.println("\nBooks Sorted by Price:");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". [" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }

        System.out.println("Total Swaps: " + swaps);
    }
    public static int searchByPrice(Book[] books, int count, double targetPrice) {

        int low = 0;
        int high = count - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (books[mid].price == targetPrice)
                return mid;

            if (books[mid].price < targetPrice)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {

        int left = 0;
        double currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < count; right++) {

            currentSum += books[right].price;

            while (currentSum >= targetCost) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= books[left].price;
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE)
            return 0;

        return minLength;
    }
    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "Data Structures", "Mark", 400),
                new Book(101, "Data Structures", "Mark", 400),
                new Book(102, "Java Basics", "James", 300),
                new Book(103, "Python Guide", "Guido", 600),
                new Book(104, "Database Systems", "Raghu", 500),
                new Book(105, "Computer Networks", "Andrew", 700)
        };

        int count = books.length;
        System.out.println("----- Task 1: Remove Duplicates -----");

        count = removeDuplicates(books, count);

        System.out.println("Unique Books Count: " + count);

        for (int i = 0; i < count; i++) {
            System.out.println("[" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }
        
        System.out.println("\n----- Task 2: Search by Title -----");
        searchByTitle(books, count, "data");
        
        System.out.println("\n----- Task 3: Sort by Price -----");
        sortByPrice(books, count);
        
        System.out.println("\n----- Task 4: Search by Price -----");

        double targetPrice = 500;

        int index = searchByPrice(books, count, targetPrice);

        if (index != -1) {
            System.out.println("Book found at index " + index + ": ["
                    + books[index].bookId + "] "
                    + books[index].title + " (Rs. "
                    + books[index].price + ")");
        } else {
            System.out.println("Book not found.");
        }
        
        System.out.println("\n----- Task 5: Sliding Window -----");

        double targetCost = 1000;

        int result = minBooksForTargetCost(books, count, targetCost);

        System.out.println("Minimum Consecutive Books Needed: " + result);
    }
}