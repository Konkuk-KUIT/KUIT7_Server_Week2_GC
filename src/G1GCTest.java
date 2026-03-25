import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    static List<Order> longLivedOrders = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== G1 GC 테스트 시작 ===");

        for (int i = 0; i < 500; i++) {
            Order shortLivedOrder = new Order("Short-Order-" + i, 512 * 1024);

            if (i % 10 == 0) {
                Order longLivedOrder = new Order("Long-Order-" + i, 5 * 1024 * 1024);
                longLivedOrders.add(longLivedOrder);
            }

            Thread.sleep(50);
        }

        System.out.println("=== G1 GC 테스트 종료 (보관된 장기 주문 수: " + longLivedOrders.size() + ") ===");
    }
}