package obj;

public class Order {

    private byte[] payload;

    public Order() {
        this.payload = new byte[256 * 1024]; // 256KB
    }
}
