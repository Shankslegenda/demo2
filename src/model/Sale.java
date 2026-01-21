package model;

public class Sale {
    private Product product;
    private Customer customer;
    private int amount;
    private double totalPrice;

    public Sale(Product product, Customer customer, int amount) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        setAmount(amount);
        this.product = product;
        this.customer = customer;
        calculateTotalPrice();
    }

    // Getters
    public Product getProduct() { return product; }
    public Customer getCustomer() { return customer; }
    public int getAmount() { return amount; }
    public double getTotalPrice() { return totalPrice; }

    // Setters with validation
    public void setAmount(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        this.amount = amount;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        this.totalPrice = product.getPrice() * amount;
    }

    // Process sale
    public void processSale() {
        if (product.getStock() < amount) {
            System.out.println("Sale failed. Not enough stock.");
            return;
        }

        if (customer.canBuy(totalPrice)) {
            customer.pay(totalPrice);
            product.sell(amount);
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed. Not enough balance.");
        }
    }

    // Print receipt
    public void printReceipt() {
        System.out.println("----- RECEIPT -----");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Product: " + product.getName());
        System.out.println("Amount: " + amount);
        System.out.println("Total: $" + totalPrice);
        System.out.println("-------------------");
    }
}