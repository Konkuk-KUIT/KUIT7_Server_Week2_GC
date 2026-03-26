import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    private static List<byte[]> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 2000; i++) {
            for (int j = 0; j < 50; j++) {
                byte[] shortLived = new byte[100 * 1024];
            }

            if (i % 10 == 0) {
                longLived.add(new byte[5 * 1024 * 1024]);
                System.out.println(i + "번째 루프: 메모리 누적 중... 현재 리스트 크기: " + longLived.size());
            }

            Thread.sleep(10);

            if (longLived.size() > 60) {
                for (int k = 0; k < 10; k++) longLived.remove(0);
            }
        }
    }
}