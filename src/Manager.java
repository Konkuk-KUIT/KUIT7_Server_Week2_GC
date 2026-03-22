package src;

public class Manager {
    private final Products products = new Products();
    private int money;
    private int productID = 1;
    private final Log log = new Log();

    public void addProduct(String name, int price) {
        products.addProduct(new Product(name, productID++, price));
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getMoney() {
        return money;
    }

    public int getSize() {
        return products.getSize();
    }

    public String printLog() {
        return log.printLog();
    }

    public Product provideProduct(User user, String name) {
        Product result = products.removeProduct(user.getMoney(), name);
        log.addLog(user, result);
        return result;
    }
}
