public class Main {
    public static void main(String[] args) {
        product apple = new product("Apple", 0.5, 10);
        Customer Aldi = new Customer("Aldi", 5.0);
        apple.showInfo();
        Aldi.showInfo();
        Sale sale = new Sale(apple, Aldi, 4);
        sale.process();
        apple.showInfo();
        Aldi.showInfo();}}