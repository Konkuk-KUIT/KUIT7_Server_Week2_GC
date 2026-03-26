import java.util.UUID;

public class Order {
    private String orderId;
    private long timestamp;
    private String status;
    private byte[] data; // 메모리 점유용 필드

    // 기본 생성자: 1KB 정도 메모리 점유
    public Order(String status) {
        this.orderId = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.data = new byte[1024]; // 1KB
    }

    // 큰 인스턴스 생성을 위한 생성자: 1MB 이상의 데이터
    public Order(String status, int sizeInBytes) {
        this(status);
        this.data = new byte[sizeInBytes];
    }
}