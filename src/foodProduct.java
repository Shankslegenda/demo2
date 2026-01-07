public class foodProduct extends Product {
    private String expiryDate;
    private boolean frozen;

    public foodProduct(int id, String name, double price, int stock, String expiryDate, boolean frozen) {
        super(id, name, price, stock); // ✅ super first
        this.expiryDate = expiryDate;
        this.frozen = frozen;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public boolean isFrozen() {
        return frozen;
    }
    @Override
    public void work() {
        System.out.println(name + " must be checked for expiry");
    }
    @Override
    public String getCategory() {
        return "Food";
    }
    @Override
    public String toString() {
        return "[Food] " + super.toString() + "  expiry=" + expiryDate + "  frozen=" + frozen;
    }
}