package model;
public abstract class BasicProduct extends Product {

    public BasicProduct(int id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public void use() {
        System.out.println(getName() + " is a generic product.");
    }

    @Override
    public String getCategory() {
        return "Basic";
    }

    @Override
    public String toString() {
        return "[Basic] " + super.toString();
    }
}