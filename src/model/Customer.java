package model;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        setName(name);
        setBalance(balance);
    }

    // Getters
    public String getName() { return name; }
    public double getBalance() { return balance; }

    // Setters with exception handling
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name;
    }

    public void setBalance(double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = balance;
    }

    // Check if customer can buy
    public boolean canBuy(double totalPrice) {
        return balance >= totalPrice;
    }

    // Pay for a purchase
    public void pay(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance)
            throw new IllegalArgumentException("Not enough balance");
        balance -= amount;
    }
}