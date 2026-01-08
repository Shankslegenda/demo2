public class drinkProduct extends Product {
    private boolean cold;
    private double volume;
    public drinkProduct(int id, String name, double price, int stock, boolean cold, double volume) {
        super(id, name, price, stock);
        this.cold = cold;
        this.volume = volume;
    }
    @Override
    public void work() {
        System.out.println(name + " is a drink product. Cold: " + cold + ", Volume: " + volume + "L");
    }
    @Override
    public String getCategory() {
        return "Drink";
    }
    public boolean isCold() { return cold; }
    public double getVolume() { return volume; }
}