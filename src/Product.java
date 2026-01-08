public abstract class Product {
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    public Product(int id, String name, double price, int stock) {
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
    }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }
    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void sell(int amount) {
        if (amount > 0 && amount <= stock) {
            stock -= amount;
            System.out.println(amount + " units sold. Remaining stock: " + stock);
        } else {
            System.out.println("Invalid amount to sell.");
        }
    }

    public abstract void work();
    public abstract String getCategory();

    @Override
    public String toString() {
        return "ID=" + id + " | " + name + " | price=" + price + " | stock=" + stock;
    }
}