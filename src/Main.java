import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Initial test data
        products.add(new foodProduct(1, "Milk", 1.2, 20, "2026-01-15", true));
        products.add(new drinkProduct(2, "Cola", 1.5, 30, true, 0.5));

        boolean running = true;
        while (running) {
            System.out.println("\n=== STORE MENU ===");
            System.out.println("1. Add Food Product");
            System.out.println("2. Add Drink Product");
            System.out.println("3. View All Products");
            System.out.println("4. Demonstrate Polymorphism");
            System.out.println("5. View Products by Type");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addFoodProduct();
                case 2 -> addDrinkProduct();
                case 3 -> viewAllProducts();
                case 4 -> demonstratePolymorphism();
                case 5 -> viewProductsByType();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }

        System.out.println("Exiting program.");
    }

    private static void addFoodProduct() {
        try {
            System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Price: "); double price = sc.nextDouble();
            System.out.print("Stock: "); int stock = sc.nextInt(); sc.nextLine();
            System.out.print("Expiry date: "); String expiry = sc.nextLine();
            System.out.print("Frozen (true/false): "); boolean frozen = sc.nextBoolean(); sc.nextLine();

            products.add(new foodProduct(id, name, price, stock, expiry, frozen));
            System.out.println("Food product added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addDrinkProduct() {
        try {
            System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Price: "); double price = sc.nextDouble();
            System.out.print("Stock: "); int stock = sc.nextInt(); sc.nextLine();
            System.out.print("Cold (true/false): "); boolean cold = sc.nextBoolean(); sc.nextLine();
            System.out.print("Volume (L): "); double volume = sc.nextDouble(); sc.nextLine();

            products.add(new drinkProduct(id, name, price, stock, cold, volume));
            System.out.println("Drink product added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n--- ALL PRODUCTS ---");
        for (Product p : products) System.out.println(p);
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Product p : products) p.work();
    }

    private static void viewProductsByType() {
        System.out.println("\nSelect type:");
        System.out.println("1. Food Product");
        System.out.println("2. Drink Product");
        int choice = sc.nextInt(); sc.nextLine();

        System.out.println("\n--- PRODUCTS BY TYPE ---");
        for (Product p : products) {
            switch (choice) {
                case 1 -> { if (p instanceof foodProduct) System.out.println(p); }
                case 2 -> { if (p instanceof drinkProduct) System.out.println(p); }
                default -> System.out.println("Invalid type selection.");
            }
        }
    }
}