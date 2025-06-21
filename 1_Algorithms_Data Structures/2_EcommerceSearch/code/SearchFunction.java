import java.util.Arrays;

class Product {
    String productId;
    String productName;
    String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " (" + category + ")";
    }
}

public class SearchFunction {

    // Linear Search
    public static Product linearSearch(Product[] products, String name) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public static Product binarySearch(Product[] products, String name) {
        int low = 0, high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(name);

            if (cmp == 0) return products[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    // Main Method
    public static void main(String[] args) {
        Product[] products = {
            new Product("101", "Laptop", "Electronics"),
            new Product("102", "Shoes", "Fashion"),
            new Product("103", "Watch", "Accessories"),
            new Product("104", "Phone", "Electronics")
        };

        // Linear Search
        System.out.println("Linear Search:");
        Product found1 = linearSearch(products, "Watch");
        System.out.println(found1 != null ? "Found: " + found1 : "Not Found");

        // Sort for Binary Search
        Arrays.sort(products, (a, b) -> a.productName.compareToIgnoreCase(b.productName));

        // Binary Search
        System.out.println("\nBinary Search:");
        Product found2 = binarySearch(products, "Watch");
        System.out.println(found2 != null ? "Found: " + found2 : "Not Found");
    }
}
