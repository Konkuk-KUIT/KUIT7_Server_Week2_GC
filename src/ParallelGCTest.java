import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<Person> longLived = new ArrayList<>();
    static List<Person> midLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        int count = 0;

        while (System.currentTimeMillis() - start < 10000) { // 🔥 10초 실행

            // 🔹 1. 단기 생존 객체 (Young GC 유도)
            for (int i = 0; i < 100; i++) {
                Person p = new Person("temp" + i, i, 256 * 1024); // 256KB
            }

            // 🔹 2. 중간 생존 객체 (Survivor 이동 유도)
            for (int i = 0; i < 20; i++) {
                midLived.add(new Person("mid" + count, count, 512 * 1024)); // 512KB
                count++;
            }

            if (midLived.size() > 200) {
                midLived.clear(); // GC 대상
            }

            // 🔹 3. 장기 생존 객체 (Old 영역 채우기)
            if (longLived.size() < 150) {
                longLived.add(new Person("long" + count, count, 1024 * 1024)); // 1MB
            }

            Thread.sleep(30);
        }

        System.out.println("Finished Parallel GC Test");
    }
}
