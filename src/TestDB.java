import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/grocery_database";
        String user = "postgres";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB CONNECTED ✅");
        } catch (Exception e) {
            System.out.println("DB NOT CONNECTED ❌");
            e.printStackTrace();
        }
    }
}