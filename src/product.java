public class product {
    private String name;
    private double price;
    private int quantity;
    public product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;}
    public double getPrice() {
        return price;}
    public int getQuantity() {
        return quantity;}
    public void sell(int amount) {
        quantity -= amount;}
    public void showInfo() {
        System.out.println(name + " | Price: " + price + " | Quantity: " + quantity);}
}