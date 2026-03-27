import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<Order> longLivedOrders = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Parallel GC 테스트를 시작");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1500; i++) {

            List<Order> tempOrders = new ArrayList<>();

            // 2. 단기 생존 객체 (512KB) 생성 후 바구니에 담기
            for(int j = 0; j < 5; j++) {
                tempOrders.add(new Order("TEMP-" + i + "-" + j, 512));
            }

            // 3. 바구니를 비워서 객체들을 Garbage로 만들기
            tempOrders.clear();

            // 4. 장기 생존 객체 (1MB = 1024KB) 생성 후 List에 보관
            if (i % 50 == 0) {
                longLivedOrders.add(new Order("SAVED-" + i, 1024));
            }

            Thread.sleep(10);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("테스트 종료. 소요 시간: " + (endTime - startTime) / 1000 + "초");
        System.out.println("보관된 장기 생존 객체 수: " + longLivedOrders.size());
    }
}