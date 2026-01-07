public abstract class Product {
    protected String name;
    protected double price;
    protected int stock;
    protected int id;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        setName(name);
        setPrice(price);
        setStock(stock);
    }
    //  validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }
    public void sell(int amount) {
        if (amount > 0 && amount <= stock) {
            stock -= amount;
        }
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    //  method for polymorphism
    public abstract void work();

    public abstract String getCategory();

    @Override
    public String toString() {
        return "ID=" + id + " | " + name + " | price=" + price + " | stock=" + stock;
    }
}