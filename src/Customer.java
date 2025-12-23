public class Customer {
    private String name;
    private String phone;
    private double balance;
    private boolean isMember;

    public Customer(String name, String phone, double balance, boolean isMember) {
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.isMember = isMember;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isMember() {
        return isMember;
    }

    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getDiscount() {
        if (isMember) {
            return 0.1; // 10% discount
        }
        return 0;
    }
}