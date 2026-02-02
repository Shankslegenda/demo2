
import database.ProductDAO;
import model.FoodProduct;
import model.DrinkProduct;

public class TestMain {
    public static void main(String[] args) {

        // 1️⃣ Create product objects
        FoodProduct apple = new FoodProduct(0, "Apple", 1.5, 100, "2026-02-10", false);
        DrinkProduct cola = new DrinkProduct(0, "Cola", 2.0, 50, true, 0.5);

        // 2️⃣ Create DAO
        ProductDAO dao = new ProductDAO();

        // 3️⃣ Insert into DB
        dao.insertFoodProduct(apple);
        dao.insertDrinkProduct(cola);

        // 4️⃣ Display all products to check
        dao.displayAllProducts();
    }
}