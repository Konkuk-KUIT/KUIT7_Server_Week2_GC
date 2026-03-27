import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    static List<Person> longLived = new ArrayList<>();
    static List<Person> midLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        int count = 0;

        while (System.currentTimeMillis() - start < 10000) {

            // 🔹 1. 단기 객체 (줄임)
            for (int i = 0; i < 50; i++) {
                Person p = new Person("temp" + i, i, 128 * 1024); // 128KB
            }

            // 🔹 2. 중간 객체
            for (int i = 0; i < 10; i++) {
                midLived.add(new Person("mid" + count, count, 256 * 1024));
                count++;
            }

            if (midLived.size() > 100) {
                midLived.clear();
            }

            // 🔹 3. 장기 객체 (핵심 수정)
            if (longLived.size() < 80) {
                longLived.add(new Person("long" + count, count, 512 * 1024));
            }

            // 🔹 살짝 여유 주기
            Thread.sleep(40);
        }

        System.out.println("Finished G1 GC Test");
    }
}
