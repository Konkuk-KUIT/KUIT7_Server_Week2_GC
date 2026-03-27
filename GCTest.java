import java.util.*;

public abstract class GCTest {
    protected final List<Object> longLived = new ArrayList<>();
    protected final int maxOldGenerationCount;
    protected final int userSize;
    protected GCTest() {
        maxOldGenerationCount = 100;
        userSize = 1024 * 1024;

    }

    static class User {
        byte[] data;
        //String id;

        User(int byteSize) {
            this.data = new byte[byteSize];
        }

    }


    // 공통 시나리오 메서드
    protected void startSimulation() throws Exception {
        System.out.println(this.getClass().getSimpleName() + " Simulation Start...");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000 && (System.currentTimeMillis() - start < 15000); i++) {

            generateShortLived();

            // 2. 장기 생존 객체 (Old 영역 유도) - 10회마다 1개씩 추가
            if (i % 10 == 0) {
                // 1MB 이상의 큰 객체 포함
                longLived.add(new User(userSize));
            }

            Thread.sleep(50);

            if (longLived.size() > maxOldGenerationCount) {
                longLived.removeFirst();
            }
        }
        System.out.println("Simulation Finished.");
    }

    private void generateShortLived() {
        // 즉시 참조 해제되는 객체들
        for (int i = 0; i < 50; i++) {
            byte[] temp = new byte[1024 * 10]; // 10KB
        }
    }

}