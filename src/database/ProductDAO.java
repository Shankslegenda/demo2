package database;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO {
    public boolean insertFoodProduct(FoodProduct food) {
        String sql = "INSERT INTO products(name, price, stock, type, expiry_date, frozen) VALUES (?, ?, ?, 'Food', ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, food.getName());
            stmt.setDouble(2, food.getPrice());
            stmt.setInt(3, food.getStock());
            stmt.setString(4, food.getExpiryDate());
            stmt.setBoolean(5, food.isFrozen());
            int rows = stmt.executeUpdate();
            stmt.close();
            if (rows > 0) {
                System.out.println("✅ FoodProduct inserted: " + food.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Insert FoodProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }
    public boolean insertDrinkProduct(DrinkProduct drink) {
        String sql = "INSERT INTO products(name, price, stock, type, cold, volume) VALUES (?, ?, ?, 'Drink', ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, drink.getName());
            stmt.setDouble(2, drink.getPrice());
            stmt.setInt(3, drink.getStock());
            stmt.setBoolean(4, drink.isCold());
            stmt.setDouble(5, drink.getVolume());
            int rows = stmt.executeUpdate();
            stmt.close();
            if (rows > 0) {
                System.out.println("✅ DrinkProduct inserted: " + drink.getName());
                 return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Insert DrinkProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);}
        return false;}
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY product_id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);}
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" Select all products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return products;}
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                rs.close();
                stmt.close();
                return p;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("❌ Select product by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }
    public boolean updateFoodProduct(FoodProduct food) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, expiry_date = ?, frozen = ? WHERE product_id = ? AND type = 'Food'";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, food.getName());
            stmt.setDouble(2, food.getPrice());
            stmt.setInt(3, food.getStock());
            stmt.setString(4, food.getExpiryDate());
            stmt.setBoolean(5, food.isFrozen());
            stmt.setInt(6, food.getId());
            int rows = stmt.executeUpdate();
            stmt.close();
            if (rows > 0) {
                System.out.println("✅ FoodProduct updated: " + food.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Update FoodProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;}
    public boolean updateDrinkProduct(DrinkProduct drink) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, cold = ?, volume = ? WHERE product_id = ? AND type = 'Drink'";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, drink.getName());
            stmt.setDouble(2, drink.getPrice());
            stmt.setInt(3, drink.getStock());
            stmt.setBoolean(4, drink.isCold());
            stmt.setDouble(5, drink.getVolume());
            stmt.setInt(6, drink.getId());
            int rows = stmt.executeUpdate();
            stmt.close();
            if (rows > 0) {
                System.out.println("✅ DrinkProduct updated: " + drink.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Update DrinkProduct failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            stmt.close();
            if (rows > 0) {
                System.out.println("✅ Product deleted ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Delete product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name ILIKE ? ORDER BY name";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);}
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("❌ Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);}
        return products;}
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ? ORDER BY price DESC";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }
            rs.close();
            stmt.close();
            System.out.println("✅ Found " + products.size() + " products in price range");
        } catch (SQLException e) {
            System.out.println("❌ Search by price range failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return products;
    }

    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price >= ? ORDER BY price DESC";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, minPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }
            rs.close();
            stmt.close();
            System.out.println("✅ Found " + products.size() + " products with minimum price");
        } catch (SQLException e) {
            System.out.println("❌ Search by minimum price failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return products;
    }
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        String type = rs.getString("type");
        if ("Food".equals(type)) {
            String expiry = rs.getString("expiry_date");
            boolean frozen = rs.getBoolean("frozen");
            return new FoodProduct(id, name, price, stock, expiry, frozen);
        } else if ("Drink".equals(type)) {
            boolean cold = rs.getBoolean("cold");
            double volume = rs.getDouble("volume");
            return new DrinkProduct(id, name, price, stock, cold, volume);
        }
        return null;
    }
    public void displayAllProducts() {
        List<Product> products = getAllProducts();
        System.out.println("\n========================================");
        System.out.println("   ALL PRODUCTS FROM DATABASE");
        System.out.println("========================================");
        if (products.isEmpty()) {
            System.out.println("No products in database.");
        } else {
            int i = 1;
            for (Product p : products) {
                System.out.print(i++ + ". ");
                p.use();
            }
        }
        System.out.println("========================================\n");}}