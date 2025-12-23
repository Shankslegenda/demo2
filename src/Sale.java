public class Sale {
    private product product;
    private Customer customer;
    private int quantity;
    private double totalPrice;

    public Sale(product product, Customer customer, int quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
        calculateTotal();
    }

    private void calculateTotal() {
        double price = product.getPrice() * quantity;
        double discount = price * customer.getDiscount();
        totalPrice = price - discount;
    }

    public boolean processSale() {
        if (product.reduceStock(quantity) && customer.pay(totalPrice)) {
            return true;
        }
        return false;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}