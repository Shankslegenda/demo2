public class Customer {
    private String name;
    private double balance;
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean canBuy(double price) {
        return balance >= price;
    }

    public void pay(double price) {
        balance -= price;
    }

    public void showInfo() {
        System.out.println(name + "    Balance: " + balance);
    }
}