public class Main {
    public static void main(String[] args) {

        product apple = new product("Apple", 100, 50, "Fruits");
        Customer customer = new Customer("Aldiyar", "87771234567", 5000, true);

        Sale sale = new Sale(apple, customer, 5);

        if (sale.processSale()) {
            System.out.println("Sale successful!");
            System.out.println("Total price: " + sale.getTotalPrice());
            System.out.println("Remaining balance: " + customer.getBalance());
            System.out.println("Remaining stock: " + apple.getQuantity());
        } else {
            System.out.println("Sale failed!");
        }
    }
}