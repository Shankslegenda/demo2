public class Main {
    public static void main(String[] args) {
        product apple = new product("Apple", 0.5, 10);
        Customer john = new Customer("John", 5.0);
        apple.showInfo();
        john.showInfo();
        Sale sale = new Sale(apple, john, 4);
        sale.process();
        apple.showInfo();
        john.showInfo();}}