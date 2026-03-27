import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    static List<Order> longLivedOrders = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("G1 GC 테스트 시작");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1500; i++) {
            // 1. 임시 바구니 생성
            List<Order> tempOrders = new ArrayList<>();

            // 2. 단기 생존 객체 담기
            for(int j = 0; j < 5; j++) {
                tempOrders.add(new Order("TEMP-" + i + "-" + j, 512));
            }

            // 3. 바구니 비우기 (GC 유발)
            tempOrders.clear();

            // 4. 장기 생존 객체 보관
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