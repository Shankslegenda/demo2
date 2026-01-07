public class drinkProduct extends Product {
    private boolean cold;
    private double volume;

    public drinkProduct(int id, String name, double price, int stock, boolean cold, double volume) {
        super(id, name, price, stock); // ✅ super first
        this.cold = cold;
        this.volume = volume;
    }
    public boolean isCold() {
        return cold;
    }
    public double getVolume() {
        return volume;
    }
    @Override
    public void work() {
        System.out.println(name + " can be served " + (cold ? "cold" : "warm"));
    }

    @Override
    public String getCategory() {
        return "Drink";
    }

    @Override
    public String toString() {
        return "[Drink] " + super.toString() + "  cold=" + cold + "  volume=" + volume + "L";
    }
}