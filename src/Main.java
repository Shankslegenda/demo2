import menu.MenuManager;
import database.ProductDAO;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Grocery Store Management System...");
        ProductDAO dao = new ProductDAO();
        System.out.println(" Initial database status:");
        dao.displayAllProducts();
        MenuManager menu = new MenuManager();
        menu.run();
        System.out.println("Thank you !");
        System.out.println(" All data is saved in PostgreSQL.");
    }
}