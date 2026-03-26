import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    // 장기 생존 객체 보관 리스트 (Old Generation 유도)
    private static final List<Order> longLivedOrders = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("--- Parallel GC Test Started ---");

        // 10초 이상 실행을 위한 루프 (50ms * 300회 = 15초)
        for (int i = 0; i < 300; i++) {
            generateWorkload(i);

            // 메모리 오버플로우 방지를 위해 오래된 객체 일부 제거
            if (i > 100 && i % 10 == 0 && !longLivedOrders.isEmpty()) {
                longLivedOrders.remove(0);
            }

            Thread.sleep(50);
            if (i % 50 == 0) System.out.println("Iteration: " + i);
        }

        System.out.println("--- Parallel GC Test Finished ---");
    }

    private static void generateWorkload(int i) {
        // 1. 단기 생존 객체: 루프 내 생성 후 즉시 참조 해제
        for (int j = 0; j < 500; j++) {
            new Order("SHORT_LIVED");
        }

        // 2. 장기 생존 객체: List에 추가하여 Old 영역 유도
        if (i % 5 == 0) {
            longLivedOrders.add(new Order("LONG_LIVED"));
        }

        // 3. 크기가 큰 객체 (Large Object): 1MB 이상
        if (i % 20 == 0) {
            new Order("LARGE_OBJECT", 1024 * 1024 * 2); // 2MB
        }
    }
}