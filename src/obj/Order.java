package obj;

public class Order {

    private byte[] payload;

    public Order() {
        // 🔥 핵심: 크기 조절 (너무 크면 터지고, 너무 작으면 GC 안됨)
        this.payload = new byte[256 * 1024]; // 256KB
    }
}