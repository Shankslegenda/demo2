package model;

public class DrinkProduct extends Product implements Acquirable {
    private boolean cold;
    private double volume;

    public DrinkProduct(int id, String name, double price, int stock, boolean cold, double volume) {
        super(id, name, price, stock);
        this.cold = cold;
        this.volume = volume;
    }

    @Override
    public void use() {
        System.out.println(name + " is a Drink Product. Cold: " + cold + ", Volume: " + volume + "L");
    }

    @Override
    public void acquire() {
        System.out.println("Acquiring drink product: " + name);
    }

    @Override
    public String getCategory() {
        return "Drink";
    }

    public boolean isCold() { return cold; }
    public double getVolume() { return volume; }
}
