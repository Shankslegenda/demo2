public class Sale {
    private Product product;
    private Customer customer;
    private int amount;
    private double totalPrice;
    public Sale(Product product, Customer customer, int amount, double totalPrice) {
        this.product = product;
        this.customer = customer;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }
    public Product getProduct() { return product; }
    public Customer getCustomer() { return customer; }
    public int getAmount() { return amount; }
    public double getTotalPrice() { return totalPrice; }
    public void setProduct(Product product) { this.product = product; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void processSale() {
        if(customer.canBuy(totalPrice)) {
            customer.pay(totalPrice);
            product.sell(amount);
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed. Not enough balance.");}}
    public void printReceipt() {
        System.out.println(customer.getName() + " bought " + amount + " of " + product.getName() + " for $" + totalPrice);
    }
    @Override
    public String toString() {
        return "Sale[product=" + product.getName() + ", customer=" + customer.getName() + ", amount=" + amount + ", totalPrice=" + totalPrice + "]";
    }
}