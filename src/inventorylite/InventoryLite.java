package inventorylite;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryLite {

    static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {

        // Sample products
        products.add(new Product(nextId++, "Laptop", 10));
        products.add(new Product(nextId++, "Keyboard", 25));
        products.add(new Product(nextId++, "Mouse", 30));

        boolean running = true;

        System.out.println("================================");
        System.out.println("       Welcome to InventoryLite");
        System.out.println("================================");

        while (running) {
            showMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateQuantity();
                    break;
                case 5:
                    removeProduct();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using InventoryLite!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    static void showMenu() {
        System.out.println();
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Quantity");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
    }

    static void addProduct() {
        System.out.println("\n--- Add Product ---");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int quantity = readInt("Enter quantity: ");

        if (name.trim().isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        Product product = new Product(nextId++, name, quantity);
        products.add(product);

        System.out.println("Product added successfully.");
    }

    static void viewProducts() {
        System.out.println("\n--- Product Inventory ---");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products) {
            product.display();
        }
    }

    static void searchProduct() {
        System.out.println("\n--- Search Product ---");

        System.out.print("Enter product name: ");
        String search = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(search)) {
                product.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    static void updateQuantity() {
        System.out.println("\n--- Update Quantity ---");

        int id = readInt("Enter product ID: ");
        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        int quantity = readInt("Enter new quantity: ");

        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        product.setQuantity(quantity);
        System.out.println("Quantity updated successfully.");
    }

    static void removeProduct() {
        System.out.println("\n--- Remove Product ---");

        int id = readInt("Enter product ID: ");
        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        products.remove(product);
        System.out.println("Product removed successfully.");
    }

    static Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
