import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== STORE MENU ===");
            System.out.println("1. Add Basic Product (Parent)");
            System.out.println("2. Add Food Product (Child 1)");
            System.out.println("3. Add Drink Product (Child 2)");
            System.out.println("4. View All Products");
            System.out.println("5. Demonstrate Polymorphism");
            System.out.println("6. View Products by Type");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBasicProduct();
                case 2 -> addFoodProduct();
                case 3 -> addDrinkProduct();
                case 4 -> viewAllProducts();
                case 5 -> demonstratePolymorphism();
                case 6 -> viewProductsByType();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }

        System.out.println("Exiting. Thank you!");
    }

    // ----------------- Menu Options -----------------

    // 1. Add Basic Product
    private static void addBasicProduct() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();

        products.add(new basicProduct(id, name, price, stock));
        System.out.println("Basic product added!");
    }

    // 2. Add Food Product
    private static void addFoodProduct() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        System.out.print("Expiry date (YYYY-MM-DD): ");
        String expiry = sc.nextLine();
        System.out.print("Frozen? (true/false): ");
        boolean frozen = sc.nextBoolean();
        sc.nextLine();

        products.add(new foodProduct(id, name, price, stock, expiry, frozen));
        System.out.println("Food product added!");
    }

    // 3. Add Drink Product
    private static void addDrinkProduct() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        System.out.print("Cold? (true/false): ");
        boolean cold = sc.nextBoolean();
        System.out.print("Volume (L): ");
        double volume = sc.nextDouble();
        sc.nextLine();

        products.add(new drinkProduct(id, name, price, stock, cold, volume));
        System.out.println("Drink product added!");
    }

    // 4. View All Products
    private static void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n--- ALL PRODUCTS ---");
        for (Product p : products) {
            System.out.println(p);

            if (p instanceof foodProduct fp) {
                System.out.println("  Expiry: " + fp.getExpiryDate());
            } else if (p instanceof drinkProduct dp) {
                System.out.println("  Volume: " + dp.getVolume() + "L");
            }
        }
    }

    // 5. Demonstrate Polymorphism
    private static void demonstratePolymorphism() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n--- POLYMORPHISM DEMO (work() method) ---");
        for (Product p : products) {
            p.work(); // SAME method called, different output
        }
    }

    // 6. View Products by Type
    private static void viewProductsByType() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\nSelect type to view:");
        System.out.println("1. Basic Product");
        System.out.println("2. Food Product");
        System.out.println("3. Drink Product");
        int typeChoice = sc.nextInt();
        sc.nextLine();

        System.out.println("\n--- PRODUCTS BY TYPE ---");
        for (Product p : products) {
            switch (typeChoice) {
                case 1 -> { if (p instanceof basicProduct) System.out.println(p); }
                case 2 -> { if (p instanceof foodProduct) System.out.println(p); }
                case 3 -> { if (p instanceof drinkProduct) System.out.println(p); }
                default -> System.out.println("Invalid type selection.");
            }
        }
    }
}