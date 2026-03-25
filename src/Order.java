public class Order {
    private String orderId;
    private byte[] payload; // 메모리 차지를 위한 더미 데이터

    public Order(String orderId, int sizeInBytes) {
        this.orderId = orderId;
        this.payload = new byte[sizeInBytes];
    }

    public String getOrderId() {
        return orderId;
    }
}