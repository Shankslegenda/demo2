public class product {
    private String name;
    private double price;
    private int quantity;
    public product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;}
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;}
    public int getQuantity() {
        return quantity;}
    public void sell(int amount) {
        quantity -= amount;}
    public void showInfo() {
        System.out.println(name + "    Price: " + price + "  Quantity: " + quantity);}
}