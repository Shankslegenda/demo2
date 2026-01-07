import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>(); // ✅ polymorphism

        products.add(new foodProduct(1, "Bread", 1.5, 10, "2026-01-10", false));
        products.add(new drinkProduct(2, "Cola", 2.0, 20, true, 0.5));

        for (Product p : products) {
            System.out.println(p);        // polymorphic toString
            p.work();                     // polymorphic behavior

            if (p instanceof foodProduct) {
                foodProduct fp = (foodProduct) p;
                System.out.println("  Expiry: " + fp.getExpiryDate());
            }

            if (p instanceof drinkProduct) {
                drinkProduct dp = (drinkProduct) p;
                System.out.println("  Volume: " + dp.getVolume());
            }
        }
    }
}