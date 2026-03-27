import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {

    // 장기 생존 객체 보관 (Old Generation 유도)
    static List<DataChunk> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("=== Parallel GC Test 시작 ===");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 600; i++) {

            // 1. 단기 생존 객체: 루프마다 생성 후 즉시 해제 → Young GC 유발
            DataChunk shortLived1 = new DataChunk("short-" + i, 256 * 1024); // 256KB
            DataChunk shortLived2 = new DataChunk("short2-" + i, 384 * 1024); // 384KB
            // 참조 끊기 (명시적으로 null 처리 — 실제로는 루프 끝나면 자동 해제)
            // shortLived1, shortLived2 → GC 대상

            // 2. 대형 객체: 1MB 이상 → Old 영역 직행 or Humongous
            DataChunk largeObj = new DataChunk("large-" + i, 1024 * 1024); // 1MB
            // 이것도 단기 생존으로 처리 (참조 해제)

            // 3. 장기 생존 객체: 10번마다 1개 보관 → Old Generation 승격 유도
            if (i % 10 == 0) {
                longLived.add(new DataChunk("long-" + i, 512 * 1024)); // 512KB 보관
            }

            // 4. 장기 생존 목록이 너무 커지면 일부 제거 (Full GC 유발 조절)
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
        System.out.printf("=== Parallel GC Test 종료 | 총 실행 시간: %dms ===%n", totalTime);
    }
}