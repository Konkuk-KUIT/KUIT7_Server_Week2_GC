package src;

public class User {
    private final String name;
    private final int userID;
    private int money;
    private final Products products = new Products();

    public User(String name, int userID) {
        this.name = name;
        this.userID = userID;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void payMoney(int money) {
        this.money -= money;
    }

    public int getMoney() {
        return money;
    }

    public int getSize() {
        return products.getSize();
    }

    public void order(Manager manager, String name) {
        Order order = new Order(this, manager);
        Product result = order.order(name);
        if(result != null) {
            payMoney(result.getPrice());
            manager.addMoney(result.getPrice());
            products.addProduct(result);
        }
    }

    @Override
    public String toString() {
        return "사용자 번호 : " + userID + ", 사용자 이름 : " + name;
    }
}
