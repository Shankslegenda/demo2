package model;

public class Sale {
    private Product product;
    private Customer customer;
    private int amount;
    private double totalPrice;

    public Sale(Product product, Customer customer, int amount) {
        // First set the product, then validate
        this.product = product;
        this.customer = customer;

        // Check for null immediately
        if (this.product == null) {
            throw new IllegalArgumentException("Product cannot be null. Product ID might not exist in database.");
        }
        if (this.customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        // Now set amount (which will call calculateTotalPrice)
        setAmount(amount);
    }

    // Getters
    public Product getProduct() {
        if (product == null) {
            throw new IllegalStateException("Product is null! This should not happen.");
        }
        return product;
    }

    public Customer getCustomer() {
        if (customer == null) {
            throw new IllegalStateException("Customer is null! This should not happen.");
        }
        return customer;
    }

    public int getAmount() { return amount; }
    public double getTotalPrice() { return totalPrice; }

    // Setters with validation
    public void setAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive. Got: " + amount);
        }
        this.amount = amount;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        // Double-check product is not null
        if (product == null) {
            throw new IllegalStateException("Cannot calculate total price: product is null!");
        }
        this.totalPrice = product.getPrice() * amount;
    }
    public void processSale() {
        if (product.getStock() < amount) {
            System.out.println("❌ Sale failed. Not enough stock.");
            System.out.println("   Available: " + product.getStock() + ", Requested: " + amount);
            return;
        }
        if (customer.canBuy(totalPrice)) {
            try {
                customer.pay(totalPrice);
                product.sell(amount);
                System.out.println("✅ Sale successful!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Sale failed: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Sale failed. Customer does not have enough balance.");
            System.out.println("   Needed: $" + totalPrice + ", Available: $" + customer.getBalance());}}
    public void printReceipt() {
        System.out.println("\n----- RECEIPT -----");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Product: " + product.getName());
        System.out.println("Amount: " + amount);
        System.out.println("Unit Price: $" + product.getPrice());
        System.out.println("Total: $" + totalPrice);
        System.out.println("Customer Balance after purchase: $" + customer.getBalance());
        System.out.println("-------------------\n");
    }
}