class Order {
    String orderId;
    String customerName;
    double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "[" + orderId + "] " + customerName + " - ₹" + totalPrice;
    }
}

public class OrderSorting {

    // Bubble Sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    // Method to print orders
    public static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // Main Method
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O1", "Alice", 1500.0),
            new Order("O2", "Bob", 3200.0),
            new Order("O3", "Charlie", 800.0),
            new Order("O4", "Diana", 4500.0)
        };

        System.out.println("Original Orders:");
        printOrders(orders);

        // Bubble Sort
        Order[] bubbleSorted = orders.clone();
        bubbleSort(bubbleSorted);
        System.out.println("\nSorted by Bubble Sort:");
        printOrders(bubbleSorted);

        // Quick Sort
        Order[] quickSorted = orders.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("\nSorted by Quick Sort:");
        printOrders(quickSorted);
    }
}
