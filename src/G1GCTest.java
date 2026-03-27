import java.util.ArrayList;
import java.util.List;

public class G1GCTest {

    // 장기 생존 객체 보관 (Old Generation 유도)
    static List<DataChunk> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("=== G1 GC Test 시작 ===");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 600; i++) {

            // 1. 단기 생존 객체: Young Region 소비 → Minor GC 유발
            DataChunk shortLived1 = new DataChunk("short-" + i, 256 * 1024); // 256KB
            DataChunk shortLived2 = new DataChunk("short2-" + i, 384 * 1024); // 384KB

            // 2. 대형 객체: G1에서는 Humongous Region으로 직행
            //    (Region 크기의 50% 초과 시 Humongous 처리)
            DataChunk hugeObj = new DataChunk("huge-" + i, 2 * 1024 * 1024); // 2MB → Humongous

            // 3. 장기 생존 객체: Old Region 승격 유도
            if (i % 10 == 0) {
                longLived.add(new DataChunk("long-" + i, 512 * 1024)); // 512KB 보관
            }

            // 4. 장기 생존 목록 조절
            if (longLived.size() > 80) {
                longLived.subList(0, 20).clear();
            }

            // 진행 상황 출력
            if (i % 50 == 0) {
                long elapsed = System.currentTimeMillis() - startTime;
                Runtime rt = Runtime.getRuntime();
                long usedMB = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
                System.out.printf("[%5dms] 반복 %d회 완료 | 장기객체 %d개 | Heap 사용 %dMB%n",
                        elapsed, i, longLived.size(), usedMB);
            }

            Thread.sleep(20);
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.printf("=== G1 GC Test 종료 | 총 실행 시간: %dms ===%n", totalTime);
    }
}