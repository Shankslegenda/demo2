package menu;

import model.*;
import database.ProductDAO;
import exception.InvalidInputException;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private Scanner scanner = new Scanner(System.in);
    private ProductDAO productDAO = new ProductDAO();

    @Override
    public void DisplayMenu() {
        System.out.println("\n=== GROCERY STORE MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Food Product");
        System.out.println("2. Add Drink Product");
        System.out.println("3. View All Products");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Search by Name");
        System.out.println("7. Search by Price Range");
        System.out.println("8. Search by Minimum Price");
        System.out.println("9. Make a Sale");
        System.out.println("10. Display All Products");
        System.out.println("11. Test Database Connection");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        int choice = 0;

        do {
            DisplayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addFoodProduct();
                    case 2 -> addDrinkProduct();
                    case 3 -> viewAllProducts();
                    case 4 -> updateProduct();
                    case 5 -> deleteProduct();
                    case 6 -> searchByName();
                    case 7 -> searchByPriceRange();
                    case 8 -> searchByMinPrice();
                    case 9 -> makeSale();
                    case 10 -> displayAllProductsFormatted();
                    case 11 -> testDatabaseConnection();
                    case 0 -> System.out.println("Exiting...");
                    default -> throw new InvalidInputException("Please choose between 0 and 11!");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Input must be a number!");
            } catch (InvalidInputException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void addFoodProduct() {
        try {
            System.out.println("\n--- ADD FOOD PRODUCT ---");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Frozen (true/false): ");
            boolean frozen = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Expiry date (YYYY-MM-DD): ");
            String expiry = scanner.nextLine();

            FoodProduct food = new FoodProduct(0, name, price, stock, expiry, frozen);
            boolean success = productDAO.insertFoodProduct(food);

            if (success) {
                System.out.println("✅ Food product added successfully!");
            } else {
                System.out.println("❌ Failed to add food product.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void addDrinkProduct() {
        try {
            System.out.println("\n--- ADD DRINK PRODUCT ---");
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

            DrinkProduct drink = new DrinkProduct(0, name, price, stock, cold, volume);
            boolean success = productDAO.insertDrinkProduct(drink);

            if (success) {
                System.out.println("✅ Drink product added successfully!");
            } else {
                System.out.println("❌ Failed to add drink product.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        System.out.println("\n--- ALL PRODUCTS ---");
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            System.out.println("Use options 1 or 2 to add products first.");
        } else {
            for (Product p : products) {
                p.use();
            }
        }
    }

    private void updateProduct() {
        try {
            System.out.println("\n--- UPDATE PRODUCT ---");
            System.out.print("Enter Product ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Product existingProduct = productDAO.getProductById(id);
            if (existingProduct == null) {
                System.out.println("❌ No product found with ID: " + id);
                System.out.println("Use option 10 to see available products and their IDs.");
                return;
            }

            System.out.println("\nCurrent Product Info:");
            existingProduct.use();

            System.out.println("\nEnter new values (press Enter to keep current):");

            System.out.print("New Name [" + existingProduct.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingProduct.getName();}
            System.out.print("New Price [" + existingProduct.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.trim().isEmpty() ?
                    existingProduct.getPrice() : Double.parseDouble(priceInput);
            System.out.print("New Stock [" + existingProduct.getStock() + "]: ");
            String stockInput = scanner.nextLine();
            int newStock = stockInput.trim().isEmpty() ?
                    existingProduct.getStock() : Integer.parseInt(stockInput);
            boolean success = false;
            if (existingProduct instanceof FoodProduct) {
                FoodProduct food = (FoodProduct) existingProduct;
                System.out.print("New Expiry Date [" + food.getExpiryDate() + "]: ");
                String expiryInput = scanner.nextLine();
                String newExpiry = expiryInput.trim().isEmpty() ?
                        food.getExpiryDate() : expiryInput;
                System.out.print("New Frozen Status [" + food.isFrozen() + "]: ");
                String frozenInput = scanner.nextLine();
                boolean newFrozen = frozenInput.trim().isEmpty() ?
                        food.isFrozen() : Boolean.parseBoolean(frozenInput);
                FoodProduct updatedFood = new FoodProduct(id, newName, newPrice, newStock, newExpiry, newFrozen);
                success = productDAO.updateFoodProduct(updatedFood);
            } else if (existingProduct instanceof DrinkProduct) {
                DrinkProduct drink = (DrinkProduct) existingProduct;
                System.out.print("New Cold Status [" + drink.isCold() + "]: ");
                String coldInput = scanner.nextLine();
                boolean newCold = coldInput.trim().isEmpty() ?
                        drink.isCold() : Boolean.parseBoolean(coldInput);
                System.out.print("New Volume [" + drink.getVolume() + "]: ");
                String volumeInput = scanner.nextLine();
                double newVolume = volumeInput.trim().isEmpty() ?
                        drink.getVolume() : Double.parseDouble(volumeInput);
                DrinkProduct updatedDrink = new DrinkProduct(id, newName, newPrice, newStock, newCold, newVolume);
                success = productDAO.updateDrinkProduct(updatedDrink);}
            if (success) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());}}
    private void deleteProduct() {
        try {
            System.out.println("\n--- DELETE PRODUCT ---");
            System.out.print("Enter Product ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            Product product = productDAO.getProductById(id);
            if (product == null) {
                System.out.println(" No product found with ID: " + id);
                System.out.println("Use option 10 to see available products and their IDs.");
                return;
            }

            System.out.println("\nProduct to delete:");
            product.use();

            System.out.print("\nAre you sure you want to delete this product? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                boolean success = productDAO.deleteProduct(id);
                if (success) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println(" Failed to delete product.");
                }
            } else {
                System.out.println(" Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());}}
    private void searchByName() {
        try {
            System.out.println("\n--- SEARCH BY NAME ---");
            System.out.print("Enter product name (or part of name): ");
            String name = scanner.nextLine();
            List<Product> results = productDAO.searchByName(name);
            if (results.isEmpty()) {
                System.out.println("No products found.");
            } else {
                System.out.println("\nFound " + results.size() + " product(s):");
                for (Product p : results) {
                    p.use();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());}}
    private void searchByPriceRange() {
        try {
            System.out.println("\n--- SEARCH BY PRICE RANGE ---");
            System.out.print("Minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Maximum price: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());
            List<Product> results = productDAO.searchByPriceRange(minPrice, maxPrice);
            if (results.isEmpty()) {
                System.out.println("No products found in this price range.");
            } else {
                System.out.println("\nFound " + results.size() + " product(s):");
                for (Product p : results) {
                    p.use();
                }
            }
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
    private void searchByMinPrice() {
        try {
            System.out.println("\n--- SEARCH BY MINIMUM PRICE ---");
            System.out.print("Minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());
            List<Product> results = productDAO.searchByMinPrice(minPrice);
            if (results.isEmpty()) {
                System.out.println("No products found with minimum price.");
            } else {
                System.out.println("\nFound " + results.size() + " product(s):");
                for (Product p : results) {
                    p.use();}}
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());}}
    private void makeSale() {
        try {
            System.out.println("\n--- MAKE A SALE ---");
            System.out.println("📋 Available Products:");
            List<Product> allProducts = productDAO.getAllProducts();
            if (allProducts.isEmpty()) {
                System.out.println("No products available to sell. Add products first.");
                return;
            }
            for (Product p : allProducts) {
                System.out.println("ID: " + p.getId() + " - " + p.getName() +
                        " ($" + p.getPrice() + ", Stock: " + p.getStock() + ")");
            }
            System.out.print("\nEnter Product ID to purchase: ");
            int productId = Integer.parseInt(scanner.nextLine());
            Product originalProduct = productDAO.getProductById(productId);
            if (originalProduct == null) {
                System.out.println(" Product not found! Please check the Product ID.");
                return;
            }
            System.out.println("\n Product Selected: " + originalProduct.getName());
            System.out.println("   Price: $" + originalProduct.getPrice());
            System.out.println("   Available Stock: " + originalProduct.getStock());
            System.out.print("\nCustomer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Customer Balance: $");
            double balance = Double.parseDouble(scanner.nextLine());
            Customer customer = new Customer(customerName, balance);
            System.out.print("Quantity to purchase: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println(" Quantity must be greater than 0.");
                return;
            }
            if (quantity > originalProduct.getStock()) {
                System.out.println("Not enough stock! Available: " + originalProduct.getStock());
                return;
            }
            Product productForSale;

            if (originalProduct instanceof FoodProduct) {
                FoodProduct food = (FoodProduct) originalProduct;
                productForSale = new FoodProduct(
                        food.getId(),
                        food.getName(),
                        food.getPrice(),
                        food.getStock(),
                        food.getExpiryDate(),
                        food.isFrozen()
                );
                System.out.println("Creating Food product for sale...");
            } else {
                DrinkProduct drink = (DrinkProduct) originalProduct;
                productForSale = new DrinkProduct(
                        drink.getId(),
                        drink.getName(),
                        drink.getPrice(),
                        drink.getStock(),
                        drink.isCold(),
                        drink.getVolume()
                );
                System.out.println(" Creating Drink product for sale...");
            }
            if (productForSale == null) {
                System.out.println(" CRITICAL ERROR: Failed to create product copy!");
                return;
            }

            System.out.println(" Product copy created successfully.");
            System.out.println(" Creating sale transaction...");

            Sale sale = new Sale(productForSale, customer, quantity);

            System.out.println(" Processing sale...");
            sale.processSale();

            if (productForSale.getStock() >= 0) {
                sale.printReceipt();
                int newStock = originalProduct.getStock() - quantity;

                if (originalProduct instanceof FoodProduct) {
                    FoodProduct updatedFood = new FoodProduct(
                            originalProduct.getId(),
                            originalProduct.getName(),
                            originalProduct.getPrice(),
                            newStock,
                            ((FoodProduct) originalProduct).getExpiryDate(),
                            ((FoodProduct) originalProduct).isFrozen()
                    );
                    boolean updateSuccess = productDAO.updateFoodProduct(updatedFood);
                    if (updateSuccess) {
                        System.out.println("Product stock updated in database.");
                    }
                } else {
                    DrinkProduct updatedDrink = new DrinkProduct(
                            originalProduct.getId(),
                            originalProduct.getName(),
                            originalProduct.getPrice(),
                            newStock,
                            ((DrinkProduct) originalProduct).isCold(),
                            ((DrinkProduct) originalProduct).getVolume()
                    );
                    boolean updateSuccess = productDAO.updateDrinkProduct(updatedDrink);
                    if (updateSuccess) {
                        System.out.println(" Product stock updated in database.");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println(" Invalid input! Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Unexpected error during sale: " + e.getMessage());
            System.out.println("Please try again with valid data.");
        }
    }

    private void displayAllProductsFormatted() {
        productDAO.displayAllProducts();
    }

    private void testDatabaseConnection() {
        System.out.println("\n--- TEST DATABASE CONNECTION ---");
        System.out.println("Testing connection to database...");

        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println(" Database connection successful!");
            System.out.println("️  No products in database. Add products using options 1 or 2.");
        } else {
            System.out.println(" Database connection successful!");
            System.out.println(" Found " + products.size() + " products in database.");
        }
    }
}