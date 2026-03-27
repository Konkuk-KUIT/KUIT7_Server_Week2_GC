public class Order {
    private String orderId;
    private byte[] payload;

    public Order(String orderId, int payloadSizeKB) {
        this.orderId = orderId;
        this.payload = new byte[payloadSizeKB * 1024]; // KB -> Byte 변환
    }
}