public class product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addStock(int amount) {
        quantity += amount;
    }

    public boolean reduceStock(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }
}