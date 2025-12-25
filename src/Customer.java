public class Customer {
    private String name;
    private double balance;
    private boolean isMember;

    public Customer(String name, double balance, boolean isMember) {
        this.name = name;
        this.balance = balance;
        this.isMember = isMember;
    }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public boolean isMember() { return isMember; }
    public void setName(String name) { this.name = name; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setMember(boolean member) { isMember = member; }
    public boolean canBuy(double price) { return balance >= price; }
    public void pay(double price) {
        if(canBuy(price)) balance -= price;
        else System.out.println("Not enough balance!");
    }
    @Override
    public String toString() {
        return "Customer[name=" + name + ", balance=" + balance +  ", isMember=" + isMember + "]";
    }
}