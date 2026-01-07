public class Customer {
    private String name;
    private double balance;
    private boolean isMember;

    public Customer(String name, double balance, boolean isMember) {
        setName(name);        // ✅ use setters
        setBalance(balance);  // ✅ use setters
        this.isMember = isMember;
    }
    // Getters
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public boolean isMember() {
        return isMember;
    }

    // Setters w validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;}}
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;}}
    public void setMember(boolean member) {
        isMember = member;
    }


    public boolean canBuy(double price) {
        return price > 0 && balance >= price;
    }

    public void pay(double price) {
        if (canBuy(price)) {
            balance -= price;
        } else {
            System.out.println("Not enough balance!");
        }
    }

    @Override
    public String toString() {
        return "Customer[name=" + name +
                ", balance=" + balance +
                ", isMember=" + isMember + "]";
    }
}