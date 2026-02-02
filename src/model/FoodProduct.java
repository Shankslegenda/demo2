package model;
public class FoodProduct extends Product implements Acquirable {
    private String expiryDate;
    private boolean frozen;

    public FoodProduct(int id, String name, double price, int stock, String expiryDate, boolean frozen) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
        this.frozen = frozen;
    }

    @Override
    public void use() {
        System.out.println(name + " is a Food Product. Expiry: " + expiryDate + ", Frozen: " + frozen);
    }

    @Override
    public void acquire() {
        System.out.println("Acquiring food product: " + name);
    }

    @Override
    public String getCategory() {
        return "Food";
    }

    public String getExpiryDate() { return expiryDate; }
    public boolean isFrozen() { return frozen; }
}
