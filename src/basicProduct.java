public class basicProduct extends Product {

    public basicProduct(int id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public void work() {
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