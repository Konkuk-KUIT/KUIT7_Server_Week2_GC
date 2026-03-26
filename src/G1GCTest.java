import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    private static List<byte[]> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("=== G1 GC 부하 테스트 시작 ===");

        for (int i = 1; i <= 2000; i++) {
            for (int j = 0; j < 50; j++) {
                byte[] shortLived = new byte[100 * 1024];
            }

            if (i % 10 == 0) {
                longLived.add(new byte[5 * 1024 * 1024]);
                if (i % 100 == 0) {
                    System.out.println(i + "번째 루프 실행 중... 리스트 크기: " + longLived.size());
                }
            }

            Thread.sleep(10);

            if (longLived.size() > 60) {
                for (int k = 0; k < 15; k++) {
                    longLived.remove(0);
                }
            }
        }
        System.out.println("=== G1 GC 부하 테스트 종료 ===");
    }
}