public class Sale {
    private Product product;
    private Customer customer;
    private int amount;
    private double totalPrice;

    public Sale(Product product, Customer customer, int amount) {
        this.product = product;
        this.customer = customer;
        setAmount(amount);
        calculateTotalPrice();
    }

    // Getters
    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    //  Setters w validation
    public void setProduct(Product product) {
        if (product != null) {
            this.product = product;
            calculateTotalPrice();
        }
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
        }
    }

    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
            calculateTotalPrice();
        }
    }
    private void calculateTotalPrice() {
        if (product != null) {
            this.totalPrice = product.getPrice() * amount;
        }
    }

    public void processSale() {
        if (product.getStock() < amount) {
            System.out.println("Sale failed. Not enough stock.");
            return;
        }

        if (customer.canBuy(totalPrice)) {
            customer.pay(totalPrice);
            product.sell(amount);
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed. Not enough balance.");
        }
    }

    public void printReceipt() {
        System.out.println("----- RECEIPT -----");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Product: " + product.getName());
        System.out.println("Amount: " + amount);
        System.out.println("Total: $" + totalPrice);
        System.out.println("-------------------");
    }

    @Override
    public String toString() {
        return "Sale[product=" + product.getName() +
                ", customer=" + customer.getName() +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice + "]";
    }
}