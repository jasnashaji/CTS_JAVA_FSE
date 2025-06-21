import java.util.HashMap;
import java.util.Scanner;

// Product class
class Product {
    String productId;
    String productName;
    int quantity;
    double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " - Qty: " + quantity + " - Price: ₹" + price;
    }
}

// InventoryManager class
public class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    // Add a product
    public void addProduct(Product product) {
        inventory.put(product.productId, product);
        System.out.println("Product added.");
    }

    // Update a product
    public void updateProduct(String productId, int quantity, double price) {
        Product p = inventory.get(productId);
        if (p != null) {
            p.quantity = quantity;
            p.price = price;
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    public void deleteProduct(String productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display all products
    public void displayInventory() {
        System.out.println("Inventory:");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    // Main method
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Product\n2. Update Product\n3. Delete Product\n4. Show Inventory\n0. Exit");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    manager.addProduct(new Product(id, name, qty, price));
                    break;

                case 2:
                    System.out.print("Product ID to update: ");
                    String updateId = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();
                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();
                    manager.updateProduct(updateId, newQty, newPrice);
                    break;

                case 3:
                    System.out.print("Product ID to delete: ");
                    String deleteId = sc.nextLine();
                    manager.deleteProduct(deleteId);
                    break;

                case 4:
                    manager.displayInventory();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
