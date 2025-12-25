public class Main {
    public static void main(String[] args) {
        Product apple = new Product("Apple", 0.5, 100, "Fruit");
        Product milk = new Product("Milk", 1.2, 50, "Dairy");
        Customer john = new Customer("John", 20.0,  true);
        Customer alice = new Customer("Alice", 5.0,  false);
        System.out.println(apple);
        System.out.println(milk);
        System.out.println(john);
        System.out.println(alice);
        Sale sale1 = new Sale(apple, john, 10, 5.0);
        Sale sale2 = new Sale(milk, alice, 3, 3.6);
        sale1.processSale();
        sale2.processSale();
        sale1.printReceipt();
        sale2.printReceipt();
        System.out.println(apple);
        System.out.println(milk);
        System.out.println(john);
        System.out.println(alice);
    }
}