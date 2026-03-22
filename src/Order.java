package src;

public class Order {
    private final User user;
    private final Manager manager;

    public Order(User user, Manager manager) {
        this.user = user;
        this.manager = manager;
    }

    public Product order(String name) {
        return manager.provideProduct(user, name);
    }
}
