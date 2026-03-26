import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    // 장기 생존 객체 보관 리스트 (Old Generation 유도)
    private static final List<Order> longLivedOrders = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("--- G1 GC Test Started ---");

        // 10초 이상 실행
        for (int i = 0; i < 300; i++) {
            generateWorkload(i);

            if (i > 100 && i % 10 == 0 && !longLivedOrders.isEmpty()) {
                longLivedOrders.remove(0);
            }

            Thread.sleep(50);
            if (i % 50 == 0) System.out.println("Iteration: " + i);
        }

        System.out.println("--- G1 GC Test Finished ---");
    }

    private static void generateWorkload(int i) {
        // 단기 생존 객체
        for (int j = 0; j < 500; j++) {
            new Order("SHORT_LIVED");
        }

        // 장기 생존 객체
        if (i % 5 == 0) {
            longLivedOrders.add(new Order("LONG_LIVED"));
        }

        // Large Object (Humongous Regions 유도)
        if (i % 20 == 0) {
            new Order("LARGE_OBJECT", 1024 * 1024 * 2);
        }
    }
}