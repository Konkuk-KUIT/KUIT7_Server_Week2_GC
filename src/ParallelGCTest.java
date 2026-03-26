import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<OrderReceipt> receipts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {

            // Case 1: 단기 생존 객체
            new User("[USER-" + i + "]");

            // Case 2: 장기 생존 객체
            if (i % 5 == 0) {
                receipts.add(new OrderReceipt("[ORDER-" + i + "]"));
            }

            // 객체 삭제
            if (receipts.size() > 100) {
                receipts.clear();
            }

            Thread.sleep(2);
        }
    }
}