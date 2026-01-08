public class foodProduct extends Product {

    private String expiryDate;
    private boolean frozen;

    public foodProduct(int id, String name, double price, int stock, String expiryDate, boolean frozen) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
        this.frozen = frozen;
    }

    @Override
    public void work() {
        System.out.println(name + " is a food product. Expiry: " + expiryDate + ", Frozen: " + frozen);
    }

    @Override
    public String getCategory() {
        return "Food";
    }

    public String getExpiryDate() { return expiryDate; }
    public boolean isFrozen() { return frozen; }
}