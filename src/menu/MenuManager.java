package menu;

import model.*;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Product> products = new ArrayList<>();
    @Override
    public void DisplayMenu() {
        System.out.println("\n=== STORE MENU ===");
        System.out.println("1. Add Food Product");
        System.out.println("2. Add Drink Product");
        System.out.println("3. View All Products");
        System.out.println("4. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        int choice = 0;

        do {
            DisplayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > 4) {
                    throw new InvalidInputException("Please choose between 1 and 4!");
                }

                switch (choice) {
                    case 1 -> addFoodProduct();
                    case 2 -> addDrinkProduct();
                    case 3 -> viewProducts();
                    case 4 -> System.out.println("Exiting...");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input must be a number!");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 4);
    }
    private void addFoodProduct(){
        int id=products.size()+1;
        System.out.println("Name:");
        String name=scanner.nextLine();

        System.out.println("Price:");
        int price=Integer.parseInt(scanner.nextLine());

        System.out.println("stock:");
        int stock=Integer.parseInt(scanner.nextLine());

        System.out.println("frozen:");
        Boolean frozen=Boolean.parseBoolean(scanner.nextLine());

        System.out.println("expiry date:");
        String expiry=scanner.nextLine();

        FoodProduct f=new FoodProduct(id,name,price,stock,expiry,frozen);
        products.add(f);

    }
    private void addDrinkProduct() {
        int id = products.size() + 1;
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Cold (true/false): ");
        boolean cold = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Volume (L): ");
        double volume = Double.parseDouble(scanner.nextLine());

        DrinkProduct d = new DrinkProduct(id, name, price, stock, cold, volume);
        products.add(d);
        System.out.println("Drink product added! ID: " + d.getId());
    }
    private void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n--- ALL PRODUCTS ---");
            for (Product p : products) {
                p.use();
            }
        }
    }
}