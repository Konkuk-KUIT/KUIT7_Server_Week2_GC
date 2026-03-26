// 장기 생존 객체
public class OrderReceipt {
    String orderId;
    byte[] receiptImage;

    OrderReceipt(String orderId) {
        this.orderId = orderId;

        // 2MB 크기의 byte[] 할당
        this.receiptImage = new byte[1024 * 1024 * 2];
    }
}
