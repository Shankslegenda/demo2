public class Sale {
    private product product;
    private Customer customer;
    private int amount;
    public Sale(product product, Customer customer, int amount) {
        this.product = product;
        this.customer = customer;
        this.amount = amount;}
    public void process() {
        double totalPrice = product.getPrice() * amount;
        if (customer.canBuy(totalPrice) && product.getQuantity() >= amount) {
            customer.pay(totalPrice);
            product.sell(amount);
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed!");}}}